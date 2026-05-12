# 🏃 Hướng dẫn chạy các bài tập

## Cách 1: Chạy từ IDE (F10/Shift+F10)

1. **Mở bất kỳ file nào trong `console/src/main/kotlin/com/example/lab1/`** (Bai1.kt, Bai2.kt, Bai3.kt, hoặc Bai4.kt)
2. **Bấm F10** để chạy
3. Kết quả sẽ hiển thị trong **Build Output**

## Cách 2: Chạy từ Console (Terminal)

```powershell
cd D:\Btapdidong\Lab1

# Chạy bài 1
.\gradlew.bat :console:bai1

# Chạy bài 2
.\gradlew.bat :console:bai2

# Chạy bài 3
.\gradlew.bat :console:bai3

# Chạy bài 4
.\gradlew.bat :console:bai4
```

## 📁 Cấu trúc dự án

- **app/** - Android app module (UI, MainActivity)
- **console/** - Console application module cho các bài tập
  - `src/main/kotlin/com/example/lab1/`
    - **Bai1.kt** - Variables, functions, conditions
    - **Bai2.kt** - Classes, inheritance
    - **Bai3.kt** - Collections, maps
    - **Bai4.kt** - Coroutines, enums

## ✅ Lưu ý quan trọng

- Các file `bai1.kt, bai2.kt, bai3.kt, bai4.kt` trong `app/src/main/java/` chỉ là placeholder
- **Hãy sửa code trong `console/src/main/kotlin/`** để thay đổi logic
- Để chạy, luôn chọn file từ **console module**, không phải app module

