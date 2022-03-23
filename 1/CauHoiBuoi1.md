<h4>1 . Trong quá trình tạo dự án Spring Boot chúng ta phải khai báo những tham số sau đây : groupID, artifactID. Ý nghĩa các tham số này là gì?</h4>

Trả lời :  
`groupID` là id của 1 nhóm dự án và nó là duy nhất trong 1 tổ chức.  
`artifactID` là id của dự án , nó chỉ định tên của dự án.

_Điểm khác nhau giữa groupID và artifactID_   
`groupID` là một thành phần XML trong tệp POM.XML của dự án Maven chỉ định id của nhóm dự án. Ngược lại, `artifactID` là một thành phần XML trong POM.XML của dự án Maven chỉ định id của dự án (artifact).  
  
<h4>2 . Tại sao phải đảo ngược tên miền trong <groupId>vn.techmaster</groupId>?</h4>  

Trả lời : Việc đảo ngược tên miền giúp lập trình viên truy cập vào thư viện cùng tên của các công ty khác nhau 1 cách dễ dàng tránh sự nhầm lẫn.

<h4>3 . SpringBoot có 2 cơ chế để quản lý thư viện. Hãy kể tên chúng?</h4>  

Trả lời :   

1. Mô hình ba lớp (three tier)
Đây là mô hình tổ chức source code rất phổ biến trong Spring Boot. Cụ thể, ứng dụng được chia làm 3 tầng (tier hoặc layer) như sau:
- Presentation layer: tầng này tương tác với người dùng, bằng View, Controller (trong MVC) hoặc API (nếu có).
- Business logic layer: Chứa toàn bộ logic của chương trình, các đa số code nằm ở đây
- Data access layer: Tương tác với database, trả về kết quả cho tầng business logic

2. Mô hình MVC 
Chia tầng presentation làm 3 phần:
- Model: các cấu trúc dữ liệu của toàn chương trình, có thể đại diện cho trạng thái của ứng dụng
- View: lớp giao diện, dùng để hiển thị dữ liệu ra cho user xem và tương tác
- Controller: kết nối giữa Model và View, điều khiển dòng dữ liệu
Dữ liệu từ Model qua Controller sau đó được gửi cho View hiển thị ra. Và ngược lại, khi có yêu cầu mới từ View, thì sẽ qua Controller thực hiện thay đổi dữ liệu của Model.

Tuy nhiên, MVC chỉ mô tả luồng đi của dữ liệu, nó không nói rõ như code đặt ở đâu (ở Model, View hay Controller), rồi lưu trữ Model vào database kiểu gì,... Do đó, đối với ứng dụng hoàn chỉnh như Spring Boot thì cần kết hợp cả mô hình MVC và 3-tier lại với nhau.

<h4>4 . File pom.xml có tác dụng gì?</h4>  

Trả lời : File `pom.xml` là nơi khai báo tất cả những gì liên quan đến dự án được cấu hình qua maven, như khai báo các `dependency`, `version của dự án`, `tên dự án`, `repossitory` …

<h4>5 . Trong file pom.xml có các thẻ dependency. Ý nghĩa của chúng là gì?</h4>  
```
    <dependency>
	    <groupId>org.springframework.boot</groupId>
	    <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
```  
Trả lời :   

<h4>6 . Ý nghĩa của @Controller là gì?</h4>  

Trả lời : Một class được đánh dấu là `@Controller` thì để khai báo Class đó là một controller và có nhiệm vụ `mapping request` trên url vào các method tương ứng trong `controller`. Ví dụ dưới đây khai báo Class HomeController là một Controller . Khi người dùng gõ vào http://localhost:8080/ thì sẽ được xử lý bởi Class HomeController. Như vậy nhiệm vụ của Controller là điều hướng các `request (yêu cầu)` người dùng vào method xử lý tương
```
    @Controller
    public class HomeController {

    @RequestMapping("/hello")
    public String hello() {
        return "Hello";
    }

}
```

<h4>7 . Ý nghĩa của @RequestMapping là gì? Nó có những tham số gì ngoài value?</h4>  

Trả lời :  Có nhiệm vụ ánh xạ các `request (yêu cầu)` người dùng vào method tương ứng trong controller.

Ngoài `value` thì `@RequestMapping` còn có các tham số khác như : 
- header
- produces 
- method
- params

<h4>8 . Ý nghĩa của @RequestResponse khi đặt trong hàm hứng request để làm gì?</h4>  

Trả lời : 


<h4>9 . Hãy trả lời khi nào thì dùng @PathVariable và khi nào nên dùng @RequestParam</h4>  

Trả lời :  
• `@RequestParam` và `@PathVariable` annotation đều được sử dụng để trích xuất dữ liệu từ request URL nhưng thật ra chúng có một điểm khác
biệt rất lớn về cách sử dụng  
• `@RequestParam` được dùng để trích xuất dữ liệu từ request query và có 4 tham số hỗ trợ: 
+)defaultValue : Đây là giá trị mặc định nếu như
giá trị của parameters trên URL rỗng. 
+)name : tên của parameters binding +)
required : Cho biết tham số này là có bắt buộc hay không, nếu "required=true" thì thiếu parameters đó request sẽ fail. 
+)value : đây là alias cho tên của thuộc tính  
• `@PathVariable` thì được dùng để trích xuất dữ liệu từ URL path.  
• sử dụng: `@RequestParam` hữu ích hơn trên một ứng dụng web truyền thống nơi dữ liệu chủ yếu được truyền trong các tham số truy vấn
trong khi `@PathVariable` phù hợp hơn với các dịch vụ web RESTful nơi URL chứa các giá trị.

<h4>10 . Thứ tự các thành phần đường dẫn @PathVariable có thể hoán đổi được không?</h4>  

Trả lời :  Thứ tự các thành phần đường dẫn `@PathVariable` không thể hoán đổi được 

<h4>11 . @GetMapping khác gì so với @PostMapping ?</h4>  

Trả lời : 
- `@PostMapping` là phiên bản chuyên biệt của chú thích `@RequestMapping` hoạt động như một phím tắt cho `@RequestMapping` (`method = RequestMethod.POST`). Các phương thức chú thích `@PostMapping` trong các lớp được chú thích `@Controller` xử lý các yêu cầu HTTP POST khớp với biểu thức URI đã cho.

- `@GetMapping` là phiên bản chuyên biệt của chú thích `@RequestMapping` hoạt động như một phím tắt cho `@RequestMapping` (`method = RequestMethod.GET`). Các phương thức chú thích `@GetMapping` trong các lớp được chú thích `@Controller` xử lý các yêu cầu HTTP GET khớp với biểu thức URI đã cho.

<h4>12 . Trong các annotation @RequestMapping, @GetMapping, @PostMapping … có tham số produces = MediaType.XXXX ý nghĩa tham số này là gì?</h4>

Trả lời :  

<h4>13 . Giải thích ý nghĩa của @RequestBody trong đoạn code dưới đây</h4>  
```
@PostMapping(value = "/message", produces = MediaType.APPLICATION_JSON_VALUE)
@ResponseBody
public Message echoMessage(@RequestBody Message message){
    return message;
}
```

Trả lời :  
`@RequestBody` được dùng để ánh xạ HttpRequest body sang một domain object tự động.  
- Theo như đoạn code trên thì Spring controller với echoMessage() method nhận vào là Message object. Spring sẽ tự động ánh xạ dữ liệu JSON trong HttpRequestBody sang một Java Type object tương ứng là Message.  
- Mặc định, tên và kiểu dữ liệu trong JSON phải trùng khớp với tên và kiểu dữ liệu trong Java Type object. 

<h4>14 . Cổng mặc định ứng dụng SpringBoot là 8080. Hãy google cách để thay đổi cổng lắng nghe mặc định</h4>  

Trả lời :  

1. Đổi port với Property file
Cách nhanh nhất và dễ nhất để tùy chỉnh Spring Boot là ghi đè các giá trị của các thuộc tính mặc định.
Đối với Port Server, thuộc tính chúng ta cần thay đổi là server.port.
Mặc định, server sẽ khởi chạy trên port 8080, chúng ta có thể thay đổi điều này bắt cách điều chỉnh giá trị server.port trong application.properties hay application.yml tương ứng.
application.properties 
`server.port=8081`
application.yml
`server:
port : 8081`
Bây giờ, server sẽ chạy trên port 8081.
Cả hai tệp đều được Spring Boot tải tự động nếu được đặt trong thư mục src / main / resources của ứng dụng Maven.

2. Environment-Specific Ports
Nếu chúng ta có một ứng dụng được triển khai trong các môi trường khác nhau, chúng tôi có thể muốn nó chạy trên các cổng khác nhau trên mỗi hệ thống.
Chúng ta có thể dễ dàng đạt được điều này bằng cách sử dụng Spring Profile với mỗi môi trường có một file cấu hình riêng.
Ví dụ application-dev.properties 
`server.port=8081`
Còn application-qa.properties sử dụng trong môi trường của qa để kiểm thử chạy ở port 8082
`server.port=8082`

3. Sử dụng CMD
Khi đóng gói và chạy ứng dụng của chúng ta dưới dạng jar, chúng ta có thể đặt đối số server.port bằng lệnh java:
`java -jar spring-5.jar --server.port=8083`
Hoặc bằng cách sử dụng cú pháp tương đương:
`java -jar -Dserver.port=8083 spring-5.jar`