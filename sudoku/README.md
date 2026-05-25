# 🧩 Sudoku - Java Swing (Kiến trúc MVC)

Chào mừng bạn đến với phiên bản tái cấu trúc trò chơi trí tuệ kinh điển **Sudoku** viết bằng Java Swing, được thiết kế lại chuẩn chỉnh theo mô hình kiến trúc **Model-View-Controller (MVC)** chuyên nghiệp.

---

## 🏗️ Cấu Trúc Dự Án MVC

Dự án đã được phân rã hoàn toàn từ file mã nguồn cồng kềnh ban đầu thành các thành phần độc lập, giúp dự án dễ bảo trì, mở rộng và phát triển:

1.  **`Tile.java` (Entity Model)**:
    - Lớp đối tượng đơn giản biểu diễn tọa độ ô vuông (`row`, `col`) trong lưới Sudoku 9x9.
    - **100% UI-free**: Không chứa bất kỳ liên kết hay thư viện đồ họa Swing/AWT nào.
2.  **`SudokuModel.java` (Model - Trạng Thái & Luật Chơi)**:
    - Lưu trữ ma trận câu đố (`puzzle`), đáp án (`solution`) và trạng thái chơi hiện tại của người chơi (`currentBoard`).
    - Quản lý chỉ số chọn số bút chì (`selectedNumber`) từ 1-9 và theo dõi tổng số lần nhập sai (`errors`).
    - Kiểm tra tính hợp lệ của lượt đi: so khớp số chọn với đáp án tại ô click. Nếu đúng, cập nhật bảng; nếu sai, tăng số lỗi.
    - **100% UI-free**: Chỉ chịu trách nhiệm quản lý dữ liệu và tính toán logic.
3.  **`SudokuView.java` (View - Giao Diện Gốc)**:
    - Kế thừa từ `JPanel` và chịu trách nhiệm xây dựng giao diện đồ họa.
    - Sử dụng thiết kế **Flat Design** hiện đại: các nút số phẳng, phối màu HSL nhẹ nhàng, thanh lịch.
    - Tự động vẽ các đường viền dày màu tối để phân vùng rõ ràng 9 lưới phụ kích thước 3x3.
    - Exposes giao diện cập nhật ô lưới (`updateTile`), làm nổi bật nút chọn số (`selectNumberButton`), cập nhật số lỗi (`updateErrors`) và thông báo chiến thắng (`showGameSolved`).
4.  **`SudokuController.java` (Controller - Điều Phối)**:
    - Đóng vai trò cầu nối: Đăng ký các sự kiện nhấp chuột (ActionListener) trên các nút số chọn và các ô lưới.
    - Khi người chơi chọn số và click ô trống, Controller điều phối Model thực hiện lượt đi và ra lệnh cho View vẽ lại số chính xác hoặc tăng số lỗi hiển thị ở thanh tiêu đề.
5.  **`App.java` (Entry Point)**:
    - Khởi tạo khung ứng dụng `JFrame`, liên kết các lớp Model, View và Controller để khởi chạy game.

---

## 🎮 Các Tính Năng & Cải Tiến Hấp Dẫn

- **Tính Năng Chơi Game Hoàn Chỉnh**: Khắc phục hoàn toàn lỗi sự kiện trống trong mã nguồn cũ (nơi click vào các ô lưới không xảy ra bất kỳ hành động nào). Giờ đây, game đã có thể chơi được hoàn chỉnh.
- **Kiểm Tra Lượt Đi Thời Gian Thực**:
  - Chọn một số từ thanh số dưới cùng (sẽ được tô xanh dương làm nổi bật).
  - Click vào ô trống. Nếu số bạn chọn chính xác so với đáp án gốc, số đó sẽ hiển thị màu xanh dương thân thiện.
  - Nếu chọn sai, số lỗi hiển thị trên bảng điều khiển sẽ tăng lên.
- **Trạng Thái Chiến Thắng**: Khi tất cả các ô được điền chính xác, ứng dụng hiển thị thông báo chúc mừng `"🎉 Sudoku Hoàn Thành!"` màu xanh lá cây vô cùng bắt mắt.

---

## 🛠️ Hướng Dẫn Biên Dịch & Chạy

Yêu cầu đã cài đặt sẵn **JDK 8** trở lên trên máy tính của bạn.

1.  Mở terminal và di chuyển đến thư mục của trò chơi:
    ```bash
    cd sudoku
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

- **Giao Diện Phẳng Premium**: Thay thế các nút nổi mặc định thô sơ bằng thiết kế phẳng tối giản, nâng tầm trải nghiệm thị giác của trò chơi.
- **Độ Phản Hồi Trực Quan Cao**: Sự tương phản màu sắc rõ rệt giữa ô số mặc định (màu xám sáng) và ô số do người chơi điền đúng (chữ xanh dương) giúp người chơi dễ dàng theo dõi tiến trình chơi game.
