# 🚀 QUICK START GUIDE

## 📦 Tổng Quan Dự Án

Đây là ứng dụng Android Compose với Room Database thực hiện đầy đủ tính năng **CRUD**:
- ✅ **Create** - Thêm mục mới
- ✅ **Read** - Xem danh sách items
- ✅ **Update** - Sửa mục (TỰ THÊM)
- ✅ **Delete** - Xóa mục

## 🗂️ Cấu Trúc Thư Mục

```
Lab10/
├── app/src/main/java/com/example/roomapp/
│   ├── data/
│   │   ├── Item.kt                  # @Entity - Model dữ liệu
│   │   ├── ItemDao.kt               # @Dao - Queries (@Insert, @Update, @Delete)
│   │   ├── ItemDatabase.kt          # @Database - Room database
│   │   └── ItemRepository.kt        # Repository - Business logic
│   │
│   ├── ui/
│   │   ├── ItemListScreen.kt        # Compose UI (Form + List)
│   │   ├── ItemViewModel.kt         # State management + business logic
│   │   └── theme/
│   │       └── Theme.kt             # App theme
│   │
│   ├── di/
│   │   └── DatabaseModule.kt        # Hilt dependency injection
│   │
│   ├── MainActivity.kt              # Entry point
│   └── RoomApp.kt                   # @HiltAndroidApp
│
├── build.gradle.kts                 # Dependencies
├── AndroidManifest.xml              # App configuration
├── README.md                        # Documentation chính
└── EDIT_FEATURE.md                  # Chi tiết tính năng SỬA
```

## 🔗 Cách Các Thành Phần Kết Nối

```
MainActivity (Hilt Entry)
    ↓
ItemListScreen (Compose UI)
    ↓ (hiltViewModel)
ItemViewModel (State & Logic)
    ↓ (Inject)
ItemRepository (Business Logic)
    ↓
ItemDao (@Dao - Queries)
    ↓
ItemDatabase (@Database - SQLite)
    ↓
items table (SQLite Storage)
```

## 🎯 Các Thao Tác Chính

### 1️⃣ THÊM (CREATE)
```
User nhập dữ liệu → Click "Thêm" 
  → ViewModel.addItem() 
  → Repository.insertItem(item) 
  → Dao.insert(item) 
  → Database lưu ✅
  → Form reset
```

### 2️⃣ HIỂN THỊ (READ)
```
App khởi động 
  → ViewModel init 
  → Repository.getAllItemsStream() 
  → Dao.getAllItems() (SELECT * FROM items)
  → Trả về Flow<List<Item>>
  → UI tự động cập nhật qua collectAsState() ✅
```

### 3️⃣ SỬA (UPDATE) - 🆕 TÍNH NĂNG MỚI
```
User click nút Edit trên item
  → ViewModel.startEditItem(item)
  → Form tự điền dữ liệu cũ
  → User chỉnh sửa
  → Click "Cập Nhật"
  → ViewModel.updateItem()
  → Repository.updateItem(item)
  → Dao.update(item) (UPDATE ... WHERE id = ?)
  → Database cập nhật ✅
  → Form reset
  → Danh sách tự update
```

### 4️⃣ XÓA (DELETE)
```
User click nút Delete
  → ViewModel.deleteItem(item)
  → Repository.deleteItem(item)
  → Dao.delete(item)
  → Database xóa ✅
  → Danh sách tự update
```

## 📝 Sửa (Edit) - Chi Tiết

### Khi nhấn nút Edit:
```kotlin
// Điều gì xảy ra trong ViewModel
_uiState.update {
    it.copy(
        isEditing = true,              // ← Chỉ thị chế độ sửa
        editingItemId = item.id,       // ← Lưu ID cần sửa
        itemName = item.name,          // ← Tự điền dữ liệu
        itemDescription = item.description,
        itemQuantity = item.quantity.toString()
    )
}

// UI tự động:
// - Tiêu đề form → "Sửa Mục"
// - Nút → "Cập Nhật" (thay vì "Thêm")
// - Xuất hiện nút "Hủy"
// - Form hiển thị dữ liệu cũ
```

### Khi nhấn "Cập Nhật":
```kotlin
// Tạo item mới với ID cũ + dữ liệu mới
val updatedItem = Item(
    id = editingItemId,           // ← Giữ ID cũ
    name = itemName,              // ← Dữ liệu mới
    description = itemDescription,
    quantity = itemQuantity.toInt()
)

// Room tự động nhận ra: "Đây là item cũ (có ID), UPDATE nó"
repository.updateItem(updatedItem)

// Kết quả: Row với ID đó được cập nhật trong database ✅
```

## 🧩 Dependencies Quan Trọng

```gradle
// Room - Database
implementation("androidx.room:room-runtime:2.6.1")
implementation("androidx.room:room-ktx:2.6.1")
kapt("androidx.room:room-compiler:2.6.1")

// Jetpack Compose - Modern UI
implementation("androidx.compose.ui:ui")
implementation("androidx.compose.material3:material3")
implementation("androidx.lifecycle:lifecycle-viewmodel-compose")

// Hilt - Dependency Injection
implementation("com.google.dagger:hilt-android:2.48")
kapt("com.google.dagger:hilt-android-compiler:2.48")

// Coroutines - Async operations
implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android")
```

## 💡 Key Concepts

| Khái Niệm | Giải Thích |
|-----------|-----------|
| **Entity** | Lớp đại diện cho table trong database |
| **DAO** | Interface với các queries (@Query, @Insert, @Update, @Delete) |
| **Database** | Room database class, cấp quyền truy cập DAO |
| **Repository** | Tầng trung gian giữa ViewModel và DAO |
| **ViewModel** | Lưu trữ state, survive configuration changes |
| **Flow** | Reactive stream cho real-time updates |
| **Coroutines** | Non-blocking async operations |
| **Hilt** | Dependency injection framework |
| **Compose** | Modern declarative UI framework |

## ✨ Đặc Điểm Nổi Bật

✅ **Real-time UI Updates** - Flow tự động update UI khi database thay đổi  
✅ **Non-blocking Operations** - Coroutines chạy trên background thread  
✅ **Type-safe Queries** - Room compile-time validates SQL queries  
✅ **State Management** - ViewModel + StateFlow quản lý UI state  
✅ **Dependency Injection** - Hilt cung cấp dependencies tự động  
✅ **Modern UI** - Jetpack Compose instead of XML layouts  
✅ **Full CRUD** - Create, Read, Update, Delete operations  
✅ **Validation** - Form validation (tên không được trống)  

## 🎓 Bước Tiếp Theo (Gợi Ý)

1. **Add Search**: Filter items theo tên
2. **Add Sort**: Sort by name, quantity, date
3. **Add Categories**: Phân loại items
4. **Add Images**: Upload hình ảnh cho items
5. **Add Sync**: Sync với Firebase/Backend
6. **Add Backup**: Export/Import database
7. **Add Notifications**: Thông báo khi item hết hàng
8. **Add Analytics**: Tracking usage

## 📚 Tham Khảo

- [Android Room Documentation](https://developer.android.com/training/data-storage/room)
- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Hilt Dependency Injection](https://developer.android.com/training/dependency-injection/hilt-android)
- [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html)

## 🆘 Troubleshooting

**Q: Gradle sync failed?**  
A: Kiểm tra JDK version, invalidate caches, rebuild

**Q: App crash khi thêm item?**  
A: Kiểm tra validation - tên có được điền không?

**Q: Edits không lưu?**  
A: Chắc chắn click "Cập Nhật", không phải "Hủy"

**Q: Danh sách không update?**  
A: Đảm bảo ViewModel collect Flow đúng cách

---

🎉 **Chúc bạn học tập vui vẻ!** Nếu có câu hỏi, tham khảo các file README.md và EDIT_FEATURE.md
