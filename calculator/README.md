# 🧮 Calculator - Java Swing (Kiến trúc MVC)

Chào mừng bạn đến với phiên bản tái cấu trúc ứng dụng **Calculator** (Máy tính bỏ túi khoa học cơ bản) viết bằng Java Swing, được thiết kế lại chuẩn chỉnh theo mô hình kiến trúc **Model-View-Controller (MVC)** chuyên nghiệp.

---

## 🏗️ Cấu Trúc Dự Án MVC

Dự án đã được phân rã hoàn toàn từ file mã nguồn cồng kềnh ban đầu thành các thành phần độc lập, giúp dự án dễ bảo trì, mở rộng và kiểm thử:

1.  **`CalculatorModel.java` (Model - Math Logic)**:
    - Lưu trữ các toán hạng đầu vào (`A`, `B`), toán tử tích cực (`operator`) và chuỗi hiển thị hiện thời (`displayValue`).
    - Thực hiện các tính toán số học (`+`, `-`, `×`, `÷`, `√`) và định dạng lại kết quả (loại bỏ phần thập phân không cần thiết như `.0`).
    - **100% UI-free**: Hoàn toàn độc lập với các nút bấm và nhãn giao diện Swing.
2.  **`CalculatorView.java` (View - Giao Diện)**:
    - Kế thừa từ `JPanel` và chịu trách nhiệm xây dựng thiết kế giao diện đồ họa.
    - Thiết kế hệ màu sắc tối giản chuẩn phong cách máy tính Apple (nền đen `customBlack`, nút chức năng xám sáng `customLightGray`, số xám tối `customDarkGray`, toán tử màu cam `customOrange`).
    - Exposes `void updateDisplay(String text)` để cập nhật màn hình hiển thị.
3.  **`CalculatorController.java` (Controller - Điều Khiển)**:
    - Bắt sự kiện click từ các nút bấm trong View, phối hợp gọi các hàm logic trong Model, và ra lệnh View cập nhật kết quả.
    - **Sửa Lỗi Logic Quan Trọng**: Khắc phục lỗi so sánh chuỗi bằng toán tử so sánh tham chiếu `==` của phiên bản cũ (vốn gây ra lỗi lặp số `"00"` trên một số môi trường chạy). Controller sử dụng phương thức so sánh chuỗi chuẩn xác `.equals()`.
4.  **`App.java` (Entry Point)**:
    - Khởi tạo khung ứng dụng `JFrame`, liên kết các thành phần MVC lại với nhau để hiển thị lên màn hình.

---

## 🎮 Các Tính Năng Hỗ Trợ

- **Các phép tính cơ bản**: Cộng (`+`), Trừ (`-`), Nhân (`×`), Chia (`÷`), Căn bậc hai (`√`).
- **Các phím chức năng**:
  - `AC`: Xóa sạch bộ nhớ và đặt lại màn hình về `0`.
  - `+/-`: Đảo dấu của số hiện tại.
  - `%`: Chia số hiển thị hiện tại cho 100.
  - `.`: Thêm dấu thập phân (ngăn chặn việc chèn nhiều dấu chấm trong cùng một số).

---

## 🛠️ Hướng Dẫn Biên Dịch & Chạy

Yêu cầu đã cài đặt sẵn **JDK 8** trở lên trên máy tính của bạn.

1.  Mở terminal và di chuyển đến thư mục của ứng dụng này:
    ```bash
    cd calculator
    ```
2.  Biên dịch tất cả các tệp `.java` từ thư mục `src/` vào thư mục đích `bin/`:
    ```bash
    javac -d bin src/*.java
    ```
3.  Chạy ứng dụng:
    ```bash
    java -cp bin App
    ```

---

## 🎨 Điểm Nổi Bật Sau Tái Cấu Trúc

- **Tính Toán An Toàn & Chuẩn Xác**: Việc chuẩn hóa việc so sánh chuỗi bằng `.equals()` giúp loại bỏ hoàn toàn các hành vi không lường trước từ việc so sánh tham chiếu chuỗi.
- **Giao Diện Apple Cao Cấp**: Kế thừa chuẩn màu sắc phong cách tối giản cao cấp giúp ứng dụng mang lại cảm giác cực kỳ premium.
