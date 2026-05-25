# 🎮 Bộ Sưu Tập Game & Tiện Ích Java Swing (Kiến Trúc MVC)

Chào mừng bạn đến với bộ sưu tập **12 trò chơi và tiện ích mini** được xây dựng bằng **Java Swing**. Toàn bộ mã nguồn trong kho lưu trữ này đã được tái cấu trúc triệt để từ các cài đặt đơn bản (monolithic) cồng kềnh sang mô hình kiến trúc **Model-View-Controller (MVC)** chuẩn mực, đồng thời nâng cấp thẩm mỹ giao diện phẳng hiện đại và sửa đổi toàn bộ các lỗi logic tiềm ẩn.

---

## 🏗️ Tổng Quan Kiến Trúc MVC Dự Án

Việc tách biệt rõ ràng giữa logic nghiệp vụ và giao diện người dùng mang lại khả năng bảo trì và mở rộng vượt trội:

*   **Model (Mô hình)**: Quản lý trạng thái dữ liệu trò chơi, tọa độ vật lý, tính toán va chạm và luật chơi cơ bản. **100% UI-free** (Không import bất kỳ thành phần đồ họa `java.awt.*` hoặc `javax.swing.*` nào).
*   **View (Giao diện)**: Kế thừa từ Swing components (thường là `JPanel`), chịu trách nhiệm tải hình ảnh, vẽ khung hình, quản lý phông chữ và thiết lập bảng màu thẩm mỹ hiện đại.
*   **Controller (Bộ điều khiển)**: Xử lý các sự kiện đầu vào của người dùng (ActionListener, KeyListener, MouseListener), điều phối trạng thái trong Model và ra lệnh cập nhật giao diện hiển thị.

---

## 📂 Danh Sách Trò Chơi & Tiện Ích

| # | Icon & Dự Án | Mô Tả Tóm Tắt | Kiến Trúc & Cải Tiến | Tài Liệu Chi Tiết |
| :---: | :--- | :--- | :--- | :---: |
| 1 | **Calculator** | Máy tính bỏ túi khoa học với đầy đủ các phép tính cơ bản và nâng cao. | Đã sửa lỗi so sánh chuỗi bằng toán tử tham chiếu `==` thành `.equals()`. Giao diện Apple cao cấp. | [Xem README](file:///Users/hoangquy/Downloads/java-game-basic/Calculator/README.md) |
| 2 | **Sudoku** | Trò chơi giải đố số 9x9 với phân tách 3x3 rõ ràng. | Khắc phục lỗi sự kiện click ô trống. Tích hợp kiểm tra tính đúng đắn theo thời gian thực và đếm lỗi. | [Xem README](file:///Users/hoangquy/Downloads/java-game-basic/Sudoku/README.md) |
| 3 | **Blackjack** | Game đánh bài Blackjack (21 điểm) đấu với Dealer máy. | Sửa lại luật phân định thắng/thua chuẩn casino quốc tế. Tách riêng bộ bài ảo khỏi hình ảnh lá bài. | [Xem README](file:///Users/hoangquy/Downloads/java-game-basic/black-jack/README.md) |
| 4 | **Chrome Dinosaur** | Game khủng long nhảy chướng ngại vật nổi tiếng của Chrome. | Quản lý tọa độ vật lý AABB độc lập. Tối ưu hóa tải tài nguyên hình ảnh động. | [Xem README](file:///Users/hoangquy/Downloads/java-game-basic/chrome-dinosaur/README.md) |
| 5 | **Flappy Bird** | Điều khiển chú chim Flappy vượt qua các đường ống nước. | Sửa lỗi tích lũy điểm số dạng số thực khiến điểm không tăng. Tách biệt chu kỳ trọng lực rơi tự do. | [Xem README](file:///Users/hoangquy/Downloads/java-game-basic/flappy-bird/README.md) |
| 6 | **Match Cards** | Trò chơi lật cặp thẻ Pokemon giống nhau trong thời gian giới hạn. | So khớp tên thẻ dạng chuỗi thuần túy thay vì so sánh đối tượng đồ họa ImageIcon như bản gốc. | [Xem README](file:///Users/hoangquy/Downloads/java-game-basic/match-cards/README.md) |
| 7 | **Minesweeper** | Game dò mìn kinh điển trên lưới ô vuông. | Triển khai thuật toán loang (Flood-fill) đệ quy hoàn toàn trong Model. Hỗ trợ cắm cờ bằng Emoji Arial. | [Xem README](file:///Users/hoangquy/Downloads/java-game-basic/minesweeper/README.md) |
| 8 | **Pac-Man** | Điều khiển Pac-Man ăn chấm vàng và né tránh bóng ma trong mê cung. | Tách lưới mê cung vật lý độc lập khỏi phần đồ họa. Đường đi tuần tra của bóng ma xử lý thông minh. | [Xem README](file:///Users/hoangquy/Downloads/java-game-basic/pacman/README.md) |
| 9 | **Snake** | Trò chơi rắn săn mồi cổ điển trên bảng lưới. | Sửa lỗi va chạm biên (off-by-one boundary check). Bổ sung tính năng nhấn nút Space để chơi lại nhanh. | [Xem README](file:///Users/hoangquy/Downloads/java-game-basic/snake/README.md) |
| 10 | **Space Invaders** | Game bắn ruồi bảo vệ trái đất trước hạm đội quái vật. | Tách biệt hoàn toàn vật lý của tàu, đạn và aliens. Tự động tăng độ khó lính mới sau mỗi đợt. | [Xem README](file:///Users/hoangquy/Downloads/java-game-basic/space-invaders/README.md) |
| 11 | **Tic-Tac-Toe** | Game cờ caro 3x3 đối kháng X-O trực quan. | Thuật toán kiểm tra thắng thua trả về danh sách ô thắng để tô màu xanh lá, tô màu cam khi hòa. | [Xem README](file:///Users/hoangquy/Downloads/java-game-basic/tic-tac-toe/README.md) |
| 12 | **Whac-A-Mole** | Game đập chuột chũi nhanh tay nhanh mắt. | Phối hợp hai luồng Timer hoạt động độc lập (Luồng hiện chuột và luồng hiện cây ăn thịt). | [Xem README](file:///Users/hoangquy/Downloads/java-game-basic/whac-a-mole/README.md) |

---

## 🛠️ Hướng Dẫn Biên Dịch & Chạy Nhanh

### Yêu cầu hệ thống
*   Cài đặt **Java Development Kit (JDK) 8** hoặc phiên bản cao hơn.
*   Một trình mô phỏng terminal hoặc Command Prompt hỗ trợ gõ lệnh.

### Quy trình chạy một trò chơi bất kỳ (Ví dụ: Sudoku)
1.  **Di chuyển vào thư mục của game**:
    ```bash
    cd Sudoku
    ```
2.  **Biên dịch toàn bộ mã nguồn**:
    Tạo thư mục đích `bin` nếu chưa có và biên dịch các tệp `.java` từ thư mục `src`:
    ```bash
    mkdir -p bin
    javac -d bin src/*.java
    ```
3.  **Khởi chạy trò chơi**:
    Chạy lớp khởi động chính `App`:
    ```bash
    java -cp bin App
    ```

---

## 💎 Điểm Nhấn Nâng Cấp Thẩm Mỹ & UX
*   **Flat Design**: Toàn bộ nút nổi 3D cổ xưa của Windows được cấu trúc lại thành các mặt phẳng màu sắc tối giản hiện đại.
*   **Bảng màu HSL hài hòa**: Sử dụng các tone màu dịu nhẹ cho mắt (như màu xám dịu, xanh Google Blue, xanh lá Pastel) đem lại trải nghiệm cao cấp.
*   **Trải nghiệm hồi sinh mượt mà**: Hầu hết các game đều được tích hợp thêm cơ chế nhấn phím Space hoặc phím tắt tương ứng để chơi lại ngay khi thua cuộc thay vì phải tắt đi bật lại app.
