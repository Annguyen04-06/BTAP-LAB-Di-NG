# 🔨 BUILD INSTRUCTIONS

## 📋 Yêu Cầu Hệ Thống

- **Android Studio** 2023.1.0 trở lên
- **Java Development Kit (JDK)** 11 trở lên
- **Android SDK** API 34 (được cài đặt qua SDK Manager)
- **Gradle** 8.1 (tự động tải)
- **Kotlin** 1.9.10 (tự động tải)

## 🚀 Các Bước Build

### Bước 1: Chuẩn Bị
1. Mở Android Studio
2. Click **File → Open** và chọn thư mục dự án `Lab10`
3. Chờ Android Studio khởi tạo project

### Bước 2: Sync Gradle
1. Android Studio sẽ tự động hiển thị thông báo "Gradle files have changed"
2. Click **Sync Now** hoặc tải **Build → Sync Project with Gradle Files**
3. Chờ quá trình sync hoàn tất (có thể mất 2-5 phút lần đầu)

### Bước 3: Build Project
#### Cách 1: Qua Android Studio UI
```
Build → Make Project
hoặc Ctrl+F9 (Windows/Linux)
hoặc Cmd+F9 (Mac)
```

#### Cách 2: Qua Terminal
```bash
cd d:\Btapdidong\Lab10
./gradlew build

# Hoặc chỉ build APK
./gradlew assembleDebug

# Build release APK
./gradlew assembleRelease
```

#### Cách 3: Build & Run
```bash
./gradlew installDebug

# Hoặc từ Android Studio
Run → Run 'app' (Shift+F10)
```

### Bước 4: Chạy Ứng Dụng

**Option 1: Trên Emulator**
1. Click **Tools → Device Manager**
2. Tạo hoặc chọn emulator
3. Click **Run** hoặc **Shift+F10**
4. Chọn emulator

**Option 2: Trên Thiết Bị Thực**
1. Bật USB Debugging trên device (Settings → Developer Options)
2. Kết nối device qua USB
3. Click **Run** hoặc **Shift+F10**
4. Chọn device

## 📦 Gradle Build Files

Dự án này sử dụng Version Catalogs (Gradle 7.4+):

```
gradle/libs.versions.toml          # Định nghĩa versions & dependencies
build.gradle.kts                   # Root build file
app/build.gradle.kts              # App module build file
settings.gradle.kts               # Gradle settings
```

## 🔍 Troubleshooting

### ❌ "Gradle sync failed"
**Giải pháp:**
1. **File → Invalidate Caches/Restart**
2. **File → Sync Settings → Restart IDE**
3. Xóa `.gradle` folder và sync lại

### ❌ "Could not find com.google.dagger:hilt-android-compiler"
**Giải pháp:**
1. Kiểm tra kết nối internet
2. **File → Settings → Repositories** thêm Maven Central
3. Sync lại

### ❌ "SDK location not found"
**Giải pháp:**
1. Tạo file `local.properties`:
```properties
sdk.dir=/path/to/android/sdk
```
2. Thay `/path/to/android/sdk` bằng đường dẫn SDK thực

### ❌ "Compilation failed with ERROR"
**Giải pháp:**
1. Kiểm tra **Build → Messages** để xem chi tiết lỗi
2. Verify JDK version: `java -version`
3. Clean project: **Build → Clean Project** rồi **Build → Rebuild Project**

### ❌ "Cannot resolve symbol 'Item', 'ItemViewModel'..." 
**Giải pháp:**
1. Sync Gradle: **Build → Sync Project with Gradle Files**
2. Invalidate caches: **File → Invalidate Caches/Restart**
3. Rebuild: **Build → Rebuild Project**

## 📊 Build Variants

Dự án cấu hình 2 build variants:

### Debug (mặc định)
- Không optimize
- Dễ debug
- Dùng cho phát triển

### Release
- Tối ưu hóa với ProGuard
- Kích thước nhỏ hơn
- Dùng cho production

```bash
# Build debug
./gradlew assembleDebug

# Build release
./gradlew assembleRelease
```

## 📁 Output Locations

Sau khi build thành công:

- **Debug APK**: `app/build/outputs/apk/debug/app-debug.apk`
- **Release APK**: `app/build/outputs/apk/release/app-release.apk`
- **Build artifacts**: `app/build/`

## 🧹 Clean Build

Nếu gặp vấn đề, hãy clean và rebuild:

```bash
./gradlew clean build

# Hoặc từ Android Studio
Build → Clean Project
Build → Rebuild Project
```

## ✅ Kiểm Tra Build Thành Công

Build thành công khi:
1. ✅ Gradle sync hoàn tất mà không có lỗi
2. ✅ Project compile thành công
3. ✅ APK được tạo tại `app/build/outputs/apk/`
4. ✅ App chạy được trên emulator/device

## 📝 Ghi Chú

- Lần build đầu tiên sẽ mất lâu hơn (tải dependencies)
- Các lần build sau sẽ nhanh hơn nhờ Gradle cache
- Nếu gặp lỗi, xem **Build → Messages** để chi tiết
- Luôn sync Gradle sau khi thay đổi `build.gradle.kts`

---

**Chúc bạn build thành công! 🎉**

Nếu còn vấn đề, tham khảo [Android Build Documentation](https://developer.android.com/build)
