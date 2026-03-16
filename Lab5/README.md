# Lab5 - Woof Material Theming

Project này đã được chuyển từ màn hình Compose mặc định sang app `Woof` theo codelab **Basic Android Kotlin Compose Material Theming**.

## Đã triển khai
- `WoofApp` với `Scaffold` + `CenterAlignedTopAppBar`
- `LazyColumn` hiển thị danh sách chó
- `Card` cho từng item
- Material 3 color scheme cho light/dark theme
- Custom `Shapes`
- Typography theo tinh thần codelab
- Edge-to-edge system bars

## Lưu ý
- Để project build được ngay mà không cần tải font/ảnh ngoài, phiên bản này dùng:
  - drawable vector cục bộ cho logo và avatar chó
  - font hệ thống gần tương đương thay cho font tải ngoài

## Chạy nhanh
```powershell
.\gradlew.bat assembleDebug
```

## Kiểm tra lỗi Kotlin/Compose
```powershell
.\gradlew.bat :app:compileDebugKotlin
```

