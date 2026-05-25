# 🚀 Space Invaders - Java Swing (Kiến trúc MVC)

Chào mừng bạn đến với phiên bản tái cấu trúc trò chơi **Space Invaders** (Bắn quái vật không gian kinh điển) viết bằng Java Swing, được thiết kế lại chuẩn chỉnh theo mô hình kiến trúc **Model-View-Controller (MVC)** chuyên nghiệp.

---

## 🏗️ Cấu Trúc Dự Án MVC

Dự án đã được phân rã hoàn toàn từ file mã nguồn cồng kềnh ban đầu thành các thành phần độc lập, giúp dự án dễ bảo trì, mở rộng và kiểm thử:

1.  **`Block.java` (Model - Entity)**:
    - Thực thể biểu diễn các hình chữ nhật vật lý trong trò chơi (Tàu vũ trụ, Quái vật, Đạn bay).
    - **100% UI-free**: Lưu trữ thông tin tọa độ (`x, y`), kích thước (`width, height`), trạng thái còn sống (`alive`), đạn đã sử dụng (`used`) và chỉ số phân loại quái vật (`alienType`). Hoàn toàn độc lập với các thư viện đồ họa Swing.
2.  **`SpaceInvadersModel.java` (Model - Game Logic)**:
    - Quản lý toàn bộ dữ liệu và quy tắc trò chơi: tọa độ tàu bay, mảng đạn bay, mảng quái vật alien, điểm số và trạng thái trò chơi (`gameOver`).
    - Tính toán logic chuyển động của quái vật không gian (khi chạm biên trái/phải sẽ tự động dịch chuyển xuống dưới 1 hàng và đảo ngược hướng di chuyển).
    - Tự động phát hiện va chạm AABB (Axis-Aligned Bounding Box) giữa các viên đạn và quái vật để tiêu diệt quái vật.
    - Tự động tăng cấp (Level Up) khi tiêu diệt hết quái vật (cộng điểm thưởng và tạo đợt quái vật đông hơn với số hàng và cột tăng dần).
    - **100% UI-free**: Hoàn toàn độc lập với phần hiển thị hình ảnh đồ họa.
3.  **`SpaceInvadersView.java` (View - Giao Diện)**:
    - Kế thừa từ `JPanel` và phụ trách việc vẽ hình ảnh lên màn hình.
    - Tải trước các tệp tài nguyên hình ảnh đồ họa dạng `.png` từ thư mục `img` (`ship.png`, `alien.png`, `alien-magenta.png`...).
    - Ánh xạ chỉ số quái vật `alienType` từ Model sang các tệp hình ảnh tương ứng (`alien-cyan`, `alien-yellow`...) để hiển thị các loại quái vật khác nhau. Vẽ tàu vũ trụ, đạn trắng và HUD hiển thị điểm số.
4.  **`SpaceInvadersController.java` (Controller - Điều Khiển)**:
    - Quản lý bộ đếm thời gian cập nhật 60 FPS (`gameLoop` - Swing Timer) điều khiển tốc độ cập nhật khung hình của trò chơi.
    - Đăng ký lắng nghe sự kiện phím từ bàn phím để di chuyển tàu vũ trụ (phím mũi tên Trái/Phải) và bắn đạn (phím `SPACE`).
    - Khi trò chơi kết thúc (Game Over), người chơi có thể nhấn **bất kỳ phím nào** để thiết lập lại màn chơi mới ngay lập tức.
5.  **`App.java` (Entry Point)**:
    - Khởi tạo ứng dụng, kết nối các thành phần Model, View, Controller và đưa khung màn hình lên hiển thị (`JFrame`).

---

## 🎮 Cách Chơi

- **Phím Mũi Tên Trái / Phải**: Di chuyển tàu vũ trụ sang trái hoặc phải để né tránh hoặc ngắm bắn quái vật.
- **Phím SPACE (Dấu cách)**: Bắn đạn la-ze thẳng lên trên tiêu diệt quái vật.
- **Hồi sinh nhanh**: Khi Game Over, nhấn bất kỳ phím nào trên bàn phím để bắt đầu lượt chơi mới ngay lập tức.

---

## 🛠️ Hướng Dẫn Biên Dịch & Chạy

Yêu cầu đã cài đặt sẵn **JDK 8** trở lên trên máy tính của bạn.

1.  Mở terminal và di chuyển đến thư mục của trò chơi này:
    ```bash
    cd space-invaders
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
