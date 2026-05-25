# 🐍 Snake Game - Java Swing (Kiến trúc MVC)

Chào mừng bạn đến với phiên bản tái cấu trúc trò chơi **Snake Game** (Rắn săn mồi cổ điển) viết bằng Java Swing, được thiết kế lại chuẩn chỉnh theo mô hình kiến trúc **Model-View-Controller (MVC)** chuyên nghiệp.

---

## 🏗️ Cấu Trúc Dự Án MVC

Dự án đã được phân rã hoàn toàn từ file mã nguồn cồng kềnh ban đầu thành các thành phần độc lập, giúp dự án dễ bảo trì, mở rộng và kiểm thử:

1.  **`Tile.java` (Model - Entity)**:
    *   Thực thể biểu diễn một ô lưới logic 2D trong game.
    *   **100% UI-free**: Lưu trữ thông tin tọa độ ô lưới đơn giản (`x, y`), hoàn toàn độc lập với các thư viện đồ họa Swing.
2.  **`SnakeGameModel.java` (Model - Game Logic)**:
    *   Quản lý toàn bộ trạng thái cốt lõi của trò chơi: kích thước bảng lưới, tọa độ đầu và thân rắn (`snakeHead`, `snakeBody`), tọa độ thức ăn (`food`), hướng di chuyển (`velocityX, velocityY`) và trạng thái trò chơi (`gameOver`).
    *   Xử lý logic rắn bò trên lưới, ăn thức ăn để phát triển chiều dài thân rắn, và sinh thức ăn ngẫu nhiên trên các ô lưới trống.
    *   **Sửa Lỗi Off-by-one**: Mã nguồn cũ thực hiện kiểm tra tràn biên bằng tọa độ pixel gây ra lỗi rắn đi lệch hẳn ngoài màn hình một ô mới chết. Chúng tôi đã chuẩn hóa việc kiểm tra va chạm biên dựa trên các ô lưới logic (`x < 0 || x >= gridCols || y < 0 || y >= gridRows`), giúp game over chính xác ngay lập tức khi rắn chạm biên.
    *   **100% UI-free**: Hoàn toàn không phụ thuộc đồ họa hiển thị.
3.  **`SnakeGameView.java` (View - Giao Diện)**:
    *   Kế thừa từ `JPanel` và phụ trách việc vẽ hình vẽ đồ họa trên màn hình.
    *   Vẽ chướng ngại vật thức ăn (màu đỏ 3D), đầu và thân rắn (màu xanh lá 3D) cùng văn bản hiển thị điểm số (Score/Game Over HUD) dựa vào tọa độ lưới của Model.
4.  **`SnakeGameController.java` (Controller - Điều Khiển)**:
    *   Quản lý bộ đếm thời gian cập nhật 100ms (`gameLoop` - Swing Timer) điều khiển tốc độ rắn di chuyển.
    *   Đăng ký lắng nghe sự kiện phím mũi tên từ bàn phím để thay đổi hướng di chuyển của rắn, tích hợp cơ chế ngăn chặn rắn tự quay đầu cắn vào thân của mình.
    *   **Tính năng bổ sung**: Cho phép nhấn phím `SPACE` khi Game Over để hồi sinh rắn và bắt đầu trò chơi mới ngay lập tức.
5.  **`App.java` (Entry Point)**:
    *   Điểm khởi tạo ứng dụng, kết nối Model, View và Controller vào khung chứa màn hình hiển thị (`JFrame`).

---

## 🎮 Cách Chơi

*   **Các Phím Mũi Tên (Lên, Xuống, Trái, Phải)**:
    *   Điều khiển hướng di chuyển của rắn tìm thức ăn đỏ. Rắn không thể tự đảo ngược hướng di chuyển ngược chiều trực tiếp (ví dụ: đang đi lên không thể đi thẳng xuống trực tiếp).
*   **Phím SPACE (Dấu cách)**:
    *   Nhấn khi trò chơi kết thúc (Game Over) để bắt đầu lại nhanh chóng mà không cần chạy lại chương trình.
*   **Điểm số**: Tương ứng với số lượng thức ăn rắn đã nuốt được (tức chiều dài thân rắn cộng thêm).

---

## 🛠️ Hướng Dẫn Biên Dịch & Chạy

Yêu cầu đã cài đặt sẵn **JDK 8** trở lên trên máy tính của bạn.

1.  Mở terminal và di chuyển đến thư mục của trò chơi này:
    ```bash
    cd snake
    ```
2.  Biên dịch tất cả các tệp `.java` từ thư mục `src/` vào thư mục đích `bin/`:
    ```bash
    javac -d bin src/*.java
    ```
3.  Chạy ứng dụng:
    ```bash
    java -cp bin App
    ```
