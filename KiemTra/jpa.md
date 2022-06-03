1. name của @Entity và @Table
   `@Entity(name = ‘EntityName’)` dùng để chỉ tên của entity được Hibernate quản lý trong khi `@Table(name = “TableName”)` chỉ đích danh tên của table dưới database.
   VD : **Hibernate: select m from EntityName m => SQL: select m from TableName m**

2. cú pháp debug trong file application.properties :

```xml
spring.jpa.show-sql=true;
```

3.Khi sử dụng h2 , làm thế nào để xem dữ liệu và viết truy vấn : sử dụng h2-console;

4.Những thuộc tính chúng ta không muốn lưu vào database thì đánh dấu bằng annotation nào ? ==> `@Transient`

5.Các tham số của `@Column` :

- `name` : tên của cột
- `nullable` : cho phép có null hay không
- `unique` : giá trị mà colum này chứa là duy nhất

6. Có 2 sự kiện mà JPA có thể bắt đc :

- Ngay trước khi đối tượng Entity lưu xuống CSDL (ngay trước lệnh INSERT) ==> `@PrePersist`

- Ngay trước khi đối tượng Entity cập nhật xuống CSDL (ngay trước lệnh UPDATE) ==> `@PreUpdate`

7. 2 annotation có thể nhúng entity này vào entity khác :

- `@Embeddable` để khai báo rằng một lớp sẽ được nhúng bởi các entity(Thực thể) khác.
- `@Embedded` được sử dụng để khai báo rằng nhúng thực thể được đánh dấu `@Embeddable` ở phía trên vào thực thể ở dưới đây.

8. `JPARepository` được extent từ interface nào ? ==> `PagingAndSortingRepository` và `QueryByExampleExecutor`.

9. Khai báo một interface repository thao tác với một Entity tên là Post, kiểu dữ liệu trường Identity là long, tuân thủ interface JpaRepository.

```java
@Repository
public interface PostRepository extends JpaRepository<Post,Long> {

}
```

10. Khi đã chọn một cột là Identity dùng `@Id` để đánh dấu, thì không cần phải dùng xác định unique dùng annotation `@Column(unique=true)` vì khi 1 một identity được đánh dấu là id thì tức là nó là khóa chính trong bảng và giá trị của nó là duy nhất không được trùng vì thế không cần phải dùng đến annotation `@Column(unique=true)`.

11. Khác biệt giữa `@Id` với `@NaturalId` :

- `@Id`, primary cần giữ nguyên không đổi .
- `@NaturalId` không phải primary và có thể được phép thay đổi, miễn đảm bảo duy nhất .

12. Có những cột không phải primary key (`@Id`) hay `@NaturalId`, dữ liệu có thể trùng lặp (unique không đảm bảo true), nhưng cần đánh chỉ mục (index) để tìm kiếm nhanh hơn vậy phải dùng annotation : `@Index`.

```java
@Entity
@Table(indexes = @Index(columnList = "firstName"))
@Getter
@Setter
public class Student implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
}
```

sau khi chạy :

```java
[main] DEBUG org.hibernate.SQL -
  create index IDX2gdkcjo83j0c2svhvceabnnoh on Student (firstName)
```

13. Annotation `@GeneratedValue` dùng để chọn cách tự sinh unique id cho primary key phải là trường kiểu **int** hoặc **long**. Nếu trường primary key có kiểu là **String**, chúng ta không thể dùng `@GeneratedValue` . Các cách đảm bảo sinh ra chuỗi có tính duy nhất : 
+ Sử dụng `UUID.randomUUID()`
+ Sử dụng `RandomStringUtils` của thư viện `org.apache.commons.lang3`.

14. 

15. `@NamedQuery` và `@Query`
+ `@NamedQuery` : Nếu không sử dụng repository interface mà chỉ dùng EntityManager để thao tác dữ liệu. Query đó sử dụng ở nhiều nơi khác nhau thì có thể dùng `@NamedQuery`.
```java
@Entity(name ="oto") //tên entity sẽ sử dụng trong câu lệnh JPQL
@Table(name = "car") //tên table sẽ sử dụng để lưu xuống bảng vật lý trong CSDL
@Data //annotation của Lombok
@NamedQuery(name = "Car.findById", query = "SELECT c FROM oto c WHERE c.id=:id")
public class Car {
@Id private long id;
private String model;
private String maker;
private int year;
}
```
+ `Query` : Với annotation `@Query` ta có thể khai báo câu query cho các method trong repository.

  + Việc khai báo câu query với `@Query` giúp ta tối ưu câu sql, và xử lý trong những trường hợp mà các method do Spring Data không thể đáp ứng:

  + Việc sử dụng các method có sẵn khi extends interface `JPARepository`, `CrudRepository`  không đáp ứng được yêu cầu.

  + Việc đặt tên method theo chuẩn Query Creation quá dài hoặc tối nghĩa. (Ví dụ bạn muốn truy vấn theo 5 điều kiện thì tên method của bạn sẽ gồm 5 điều kiện đó => quá dài)

```java
@Query("SELECT o FROM oto AS o WHERE o.year=:year")
List<Car> listCarInYear(@Param("year") int year);
// Phải ghi rõ domain, package của kiểu trả về
vn.techmaster.demojpa.model.mapping.MakerCount
@Query("SELECT new vn.techmaster.demojpa.repository.MakerCount(c.maker, COUNT(*))
" +
"FROM oto AS c GROUP BY c.maker ORDER BY c.maker ASC")
List<MakerCount> countByMaker();
@Query("SELECT new vn.techmaster.demojpa.repository.MakerCount(c.maker, COUNT(*))
" +
"FROM oto AS c GROUP BY c.maker ORDER BY COUNT(*) DESC")
List<MakerCount> topCarMaker(Pageable pageable);
//Chú ý PSQL không hỗ trợ cú pháp SELECT TOP hay LIMIT, thay vào đó phải truyền
vào Pageable pageable
```

16. Cách tạo custom method implemetations cho Jpa Repository
