# 🦖 Chrome Dinosaur - Java Swing (Kiến trúc MVC)

Chào mừng bạn đến với phiên bản tái cấu trúc trò chơi **Chrome Dinosaur** (Khủng long chạy bộ huyền thoại từ Google Chrome) viết bằng Java Swing, được thiết kế lại chuẩn chỉnh theo mô hình kiến trúc **Model-View-Controller (MVC)** chuyên nghiệp.

---

## 🏗️ Cấu Trúc Dự Án MVC

Dự án đã được phân rã hoàn toàn từ file mã nguồn cồng kềnh ban đầu thành các thành phần độc lập, giúp dễ bảo trì, mở rộng và kiểm thử:

1.  **`Block.java` (Model - Entity)**:
    - Đối tượng thực thể cơ bản đại diện cho hình chữ nhật vật lý trong không gian 2D (Khủng long, Xương rồng).
    - **100% UI-free**: Không chứa bất kỳ thư viện giao diện đồ họa nào (`java.awt.*` hay `javax.swing.*`), lưu trữ thông tin thuần túy về kích thước (`x, y, width, height`) và loại vật thể (`type`).
2.  **`ChromeDinosaurModel.java` (Model - Game Logic)**:
    - Quản lý toàn bộ trạng thái trò chơi bao gồm tọa độ khủng long, mảng xương rồng, điểm số và trạng thái dừng cuộc chơi (`gameOver`).
    - Tính toán chuyển động cơ học (Trọng lực `gravity = 1`, nhảy với vận tốc `velocityY = -17`, cuộn cảnh xương rồng `velocityX = -12`).
    - Xử lý thuật toán va chạm AABB (Axis-Aligned Bounding Box) chính xác giữa Khủng long và các chướng ngại vật.
    - **100% UI-free**: Hoàn toàn độc lập với phần hiển thị hình ảnh đồ họa.
3.  **`ChromeDinosaurView.java` (View - Giao Diện)**:
    - Kế thừa từ `JPanel` và chịu trách nhiệm vẽ đồ họa.
    - Tải trước các tệp tài nguyên hình ảnh động dạng `.gif` và ảnh tĩnh `.png` từ thư mục `img` (`dino-run.gif`, `dino-jump.png`, `dino-dead.png`, `cactus1.png`...).
    - Tự động ánh xạ trạng thái vật lý của Model sang hình ảnh hiển thị tương ứng (ví dụ: khủng long đang ở trên không sẽ vẽ ảnh nhảy, khi va chạm vẽ ảnh chết, đang chạy vẽ ảnh động gif chạy bộ).
4.  **`ChromeDinosaurController.java` (Controller - Điều Khiển)**:
    - Liên kết Model và View.
    - Quản lý vòng lặp cập nhật vật lý 60 FPS (`gameLoop` - Swing Timer) và bộ sinh chướng ngại vật ngẫu nhiên sau mỗi 1.5 giây (`placeCactusTimer`).
    - Lắng nghe sự kiện bàn phím từ người chơi: bấm phím `SPACE` để nhảy lên hoặc nhấn `SPACE` khi chết để bắt đầu lại trò chơi lập tức.
5.  **`App.java` (Entry Point)**:
    - Điểm khởi tạo ứng dụng, tiến hành kết nối ba mảnh ghép Model, View, Controller và đưa khung màn hình lên hiển thị (`JFrame`).

---

## 🎮 Cách Chơi

- **Phím SPACE (Dấu cách)**:
  - **Khi đang chạy**: Nhảy lên để né các bụi cây xương rồng xuất hiện trên đường đi.
  - **Khi trò chơi kết thúc (Game Over)**: Nhấn để hồi sinh khủng long và bắt đầu lượt chơi mới ngay lập tức.
- **Điểm số**: Tự động tăng dần theo thời gian sinh tồn. Càng sống sót lâu, điểm số của bạn càng cao!

---

## 🛠️ Hướng Dẫn Biên Dịch & Chạy

Yêu cầu đã cài đặt sẵn **JDK 8** trở lên trên máy tính của bạn.

1.  Mở terminal và di chuyển đến thư mục của trò chơi này:
    ```bash
    cd chrome-dinosaur
    ```
2.  Biên dịch tất cả các tệp `.java` từ thư mục `src/` vào thư mục đích `bin/`:
    ```bash
    javac -d bin src/*.java
    cp -r resources/* bin
    ```
3.  Chạy ứng dụng:
    ```bash
    java -cp bin App
    ```
