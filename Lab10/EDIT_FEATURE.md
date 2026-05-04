# 📝 TÍNH NĂNG SỬA (EDIT) - CHI TIẾT TRIỂN KHAI

## Mô Tả Tính Năng

Tính năng **Sửa (Edit/Update)** cho phép người dùng:
1. Chỉnh sửa thông tin của một mục đã lưu
2. Cập nhật dữ liệu vào database
3. Hủy bỏ thao tác sửa

## 🎯 Các Thành Phần Chính

### 1. **ItemUiState** - Quản Lý Trạng Thái
```kotlin
data class ItemUiState(
    val items: List<Item> = emptyList(),
    val itemName: String = "",
    val itemDescription: String = "",
    val itemQuantity: String = "",
    val isEditing: Boolean = false,          // 🔴 Chỉ thị chế độ sửa
    val editingItemId: Int? = null,          // 🔴 ID của item đang sửa
    val selectedItem: Item? = null           // 🔴 Item đang được chỉnh sửa
)
```

### 2. **ItemViewModel** - Logic Sửa
```kotlin
// Bắt đầu chế độ sửa
fun startEditItem(item: Item) {
    _uiState.update {
        it.copy(
            isEditing = true,
            editingItemId = item.id,
            itemName = item.name,           // ← Điền dữ liệu cũ
            itemDescription = item.description,
            itemQuantity = item.quantity.toString(),
            selectedItem = item
        )
    }
}

// Cập nhật dữ liệu vào database
fun updateItem() {
    if (_uiState.value.itemName.isNotBlank() && 
        _uiState.value.editingItemId != null) {
        
        val updatedItem = Item(
            id = _uiState.value.editingItemId!!,  // ← Giữ ID cũ
            name = _uiState.value.itemName,        // ← Dữ liệu mới
            description = _uiState.value.itemDescription,
            quantity = _uiState.value.itemQuantity.toIntOrNull() ?: 0
        )
        
        viewModelScope.launch {
            itemRepository.updateItem(updatedItem)  // ← Update DB
            clearInputs()                            // ← Reset form
        }
    }
}

// Hủy chế độ sửa
fun cancelEdit() {
    clearInputs()
}
```

### 3. **ItemRepository** - Database Layer
```kotlin
suspend fun updateItem(item: Item) {
    itemDao.update(item)  // ← SQL UPDATE command
}
```

### 4. **ItemDao** - Query Database
```kotlin
@Update
suspend fun update(item: Item)  // ← Room auto-generates SQL UPDATE
```

### 5. **UI Components** - Giao Diện

#### A. Form thay đổi theo trạng thái
```kotlin
ItemInputForm(
    // ... các fields input ...
    isEditing = uiState.isEditing,  // ← Kiểm tra chế độ sửa
    onSave = { viewModel.saveItem() },
    onCancel = { viewModel.cancelEdit() }
)
```

#### B. Nút trong form tự động đổi
```kotlin
Button(
    onClick = onSave,
    modifier = Modifier
        .weight(1f)
        .height(48.dp),
    enabled = itemName.isNotBlank()
) {
    Text(if (isEditing) "Cập Nhật" else "Thêm")  // ← Tự động đổi text
}

// Nút "Hủy" chỉ hiển thị khi isEditing = true
if (isEditing) {
    OutlinedButton(
        onClick = onCancel,
        modifier = Modifier
            .weight(1f)
            .height(48.dp)
    ) {
        Text("Hủy")
    }
}
```

#### C. Item Card với nút Edit
```kotlin
ItemCard(
    item = item,
    onEdit = { viewModel.startEditItem(item) },  // ← Bắt đầu sửa
    onDelete = { viewModel.deleteItem(item) }
)

// Trong ItemCard:
IconButton(onClick = onEdit) {
    Icon(
        imageVector = Icons.Default.Edit,
        contentDescription = "Sửa"
    )
}
```

## 🔄 Luồng Sử Dụng (User Flow)

```
┌─────────────────────────────────────┐
│  Danh sách items hiển thị           │
│  ┌─────────────────────────────────┐│
│  │ Item 1: Tên, mô tả, số lượng  ✏️│
│  │ [Nút Sửa] [Nút Xóa]            ││
│  └─────────────────────────────────┘│
└─────────────────────────────────────┘
              ↓ User click Nút Sửa
┌─────────────────────────────────────┐
│  FORM ĐỔI THÀNH:                    │
│  Tiêu đề: "Sửa Mục"                 │
│                                     │
│  Tên Mục: [Item 1...]  ← Tự điền   │
│  Mô Tả: [Mô tả cũ...]  ← Tự điền  │
│  Số Lượng: [số cũ]     ← Tự điền  │
│                                     │
│  [Cập Nhật] [Hủy]  ← Nút thay đổi  │
└─────────────────────────────────────┘
    ↙ Hủy            ↘ Cập Nhật
    ↓                ↓
Form reset    Database UPDATE
Danh sách     Danh sách
gốc           cập nhật ngay
```

## 💾 Cách Hoạt Động - Technical Details

### Update trong Room
```kotlin
// Khi Room gặp @Update:
// 1. Kiểm tra primary key (id)
// 2. Tìm row có ID đó
// 3. UPDATE tất cả fields khác
// 4. Trả về số rows bị ảnh hưởng

@Update
suspend fun update(item: Item)
// ↓ Tương đương SQL:
// UPDATE items SET name = ?, description = ?, quantity = ? WHERE id = ?
```

### Flow và State Management
```kotlin
// ViewModel luôn emit state mới
_uiState.update { currentState ->
    currentState.copy(/* những field thay đổi */)
}

// UI tự động recompose khi state thay đổi
val uiState by viewModel.uiState.collectAsState()
// ↓ Compose tự động gọi lại @Composable khi state thay đổi
```

## ✅ Validation & Error Handling

### Form Validation
```kotlin
// Không cho lưu nếu tên trống
enabled = itemName.isNotBlank()

// Quantity convert an toàn
quantity = _uiState.value.itemQuantity.toIntOrNull() ?: 0
// ↑ Nếu không phải số → mặc định 0
```

### Safe Updates
```kotlin
fun updateItem() {
    // Kiểm tra dữ liệu hợp lệ
    if (_uiState.value.itemName.isNotBlank() && 
        _uiState.value.editingItemId != null) {  // ← An toàn NULL pointer
        
        // Chỉ update khi dữ liệu đầy đủ
        // ...
    }
}
```

## 🧪 Cách Test Tính Năng Sửa

```
1. Chạy app
2. Thêm một item (VD: "Bánh mì", "Bánh tươi", "50")
3. Click nút "Sửa" trên item vừa thêm
   ✓ Form đổi title → "Sửa Mục"
   ✓ Form tự điền dữ liệu cũ
   ✓ Nút "Thêm" đổi thành "Cập Nhật"
   ✓ Xuất hiện nút "Hủy"
4. Chỉnh sửa (VD: Đổi số lượng thành "75")
5. Click "Cập Nhật"
   ✓ Form reset
   ✓ Danh sách cập nhật (số lượng = 75)
6. Test "Hủy": Sửa rồi click "Hủy"
   ✓ Form reset
   ✓ Dữ liệu item không thay đổi
```

## 📊 Trạng Thái Form - Bảng So Sánh

| Trạng Thái | Thêm Mới | Sửa |
|-----------|----------|-----|
| Tiêu đề | "Thêm Mục Mới" | "Sửa Mục" |
| Nút Primary | "Thêm" | "Cập Nhật" |
| Nút Phụ | Không có | "Hủy" |
| Input fields | Trống | Tự điền dữ liệu cũ |
| Action | INSERT new item | UPDATE existing item |
| DB ID | Auto-generated | Giữ ID cũ |

## 🎨 Gợi Ý Mở Rộng

1. **Toast notifications** khi sửa thành công
2. **Confirmation dialog** trước khi xóa
3. **Undo/Redo** functionality
4. **Auto-save** trước khi sửa
5. **Timestamp** - thêm ngày/giờ sửa cuối cùng
6. **History** - lưu lịch sử thay đổi

Chúc bạn thành công! 🚀
