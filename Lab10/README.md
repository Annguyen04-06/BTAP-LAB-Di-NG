# Room Database CRUD App - Android Compose

Ứng dụng Android Compose với Room Database để quản lý dữ liệu với các tính năng **Thêm (Create), Xóa (Delete), Sửa (Update)**.

## 📋 Tính Năng

### 1. ✅ Thêm Mục Mới (CREATE)
- Nhập tên, mô tả, số lượng
- Click nút "Thêm" để lưu vào database
- Form tự động clear sau khi lưu

### 2. ✏️ Sửa Mục (UPDATE) - **TÍNH NĂNG MỚI**
- Click nút "Sửa" (Edit icon) trên mục cần chỉnh sửa
- Form tự động điền dữ liệu từ mục đã chọn
- Tiêu đề form đổi thành "Sửa Mục"
- Nút "Thêm" đổi thành "Cập Nhật"
- Xuất hiện nút "Hủy" để thoát chế độ sửa
- Click "Cập Nhật" để lưu thay đổi
- Click "Hủy" để hủy sửa và reset form

### 3. ❌ Xóa Mục (DELETE)
- Click nút "Xóa" (Delete icon) để xóa mục
- Xóa ngay lập tức khỏi database
- Nếu đang sửa mục được xóa, form tự động reset

## 📁 Cấu Trúc Dự Án

```
com/example/roomapp/
├── data/
│   ├── Item.kt              # Entity class (Model dữ liệu)
│   ├── ItemDao.kt           # Database Access Object (CRUD queries)
│   ├── ItemDatabase.kt      # Room Database class
│   └── ItemRepository.kt    # Repository pattern (business logic)
├── ui/
│   ├── ItemListScreen.kt    # UI Compose (Form + List)
│   ├── ItemViewModel.kt     # ViewModel (state management)
│   └── theme/
│       └── Theme.kt         # Compose theme
├── di/
│   └── DatabaseModule.kt    # Hilt Dependency Injection
├── MainActivity.kt          # Entry point
└── RoomApp.kt              # Application class
```

## 🔄 Luồng Hoạt Động - EDIT (SỬA)

```
1. User click Edit button trên Item
   ↓
2. ItemViewModel.startEditItem(item) được gọi
   ↓
3. UiState cập nhật:
   - isEditing = true
   - editingItemId = item.id
   - itemName, itemDescription, itemQuantity được điền
   ↓
4. Form cập nhật hiển thị dữ liệu cũ
   ↓
5a. User chỉnh sửa và click "Cập Nhật"
    → ItemViewModel.updateItem() gọi repository.updateItem()
    ↓
5b. User click "Hủy"
    → ItemViewModel.cancelEdit()
    → Form reset
```

## 🛠️ Các Phương Thức Chính

### ItemViewModel
```kotlin
// Cập nhật input fields
updateItemName(name: String)
updateItemDescription(description: String)
updateItemQuantity(quantity: String)

// CRUD operations
addItem()           // Thêm mục mới
deleteItem(item)    // Xóa mục
startEditItem(item) // Bắt đầu chế độ sửa
updateItem()        // Lưu thay đổi khi sửa
cancelEdit()        // Hủy chế độ sửa
saveItem()          // Thống nhất (thêm hoặc sửa)
```

### ItemRepository
```kotlin
insertItem(item)        // Thêm
deleteItem(item)        // Xóa
updateItem(item)        // Sửa
getAllItemsStream()     // Lấy tất cả items
getItemStream(id)       // Lấy item theo ID
```

## 🗄️ Database Schema

**Table: items**
| Column | Type | Notes |
|--------|------|-------|
| id | INT | Primary Key (Auto-generated) |
| name | TEXT | Tên mục (bắt buộc) |
| description | TEXT | Mô tả chi tiết |
| quantity | INT | Số lượng |

## 📦 Dependencies

- **Room**: Database persistence
- **Jetpack Compose**: Modern UI
- **Hilt**: Dependency Injection
- **Coroutines**: Async operations
- **ViewModel & LiveData**: State management
- **Material3**: Design components

## ✨ Điểm Nổi Bật

- ✅ Full CRUD operations (Create, Read, Update, Delete)
- ✅ Real-time UI updates với Flow
- ✅ Giao diện hiện đại với Compose
- ✅ Dependency Injection với Hilt
- ✅ Coroutines cho non-blocking database operations
- ✅ UI State management với ViewModel
- ✅ Validation (form không cho lưu nếu tên trống)
- ✅ Toast-like feedback cho từng action

## 🚀 Cách Chạy

1. Clone hoặc copy code vào Android Studio
2. Sync Gradle
3. Build and run trên emulator hoặc device
4. Thêm, sửa, xóa items từ giao diện

## 📝 Ghi Chú

- Tất cả database operations chạy trên background thread (Coroutines)
- UI tự động cập nhật qua Flow khi database thay đổi
- Form input tự động validate (tên không được để trống)
- Hỗ trợ tiếng Việt hoàn toàn

Chúc bạn học tập vui vẻ! 🎉
