### 构造方法
在 Kotlin 中的一个类可以有一个主构造函数以及一个或多个次构造函数。主构造函数是类头的一部分：它跟在类名（与可选的类型参数）后。
class Person constructor(firstName: String) { /*……*/ }
如果主构造函数没有任何注解或者可见性修饰符，可以省略这个 constructor 关键字。
class Person(firstName: String) { /*……*/ }
主构造函数不能包含任何的代码。初始化的代码可以放到以 init 关键字作为前缀的初始化块（initializer blocks）中
主构造的参数可以在初始化块中使用。它们也可以在类体内声明的属性初始化器中使用

类也可以声明前缀有 constructor的次构造函数：

lateinit（延迟加载）

默认情况下，Kotlin 类是最终（final）的：它们不能被继承。 要使一个类可继承，请用 open 关键字标记它。
·为类增加open，class就可以被继承了
·为方法增加open，那么方法就可以被重写了


object 用object 修饰的类为静态类，里面的方法和变量都为静态的。
companion object 修饰为伴生对象,伴生对象在类中只能存在一个，类似于java中的静态方法 Java 中使用类访问静态成员，静态方法。
data class  数据类，只保存一些数据字段，类似于java bean，注意后面是（） ，不是{}


只有一个抽象方法的接口称为函数式接口或 SAM（单一抽象方法）接口。函数式接口可以有多个非抽象成员，但只能有一个抽象成员。
用 fun 修饰符在 Kotlin 中声明一个函数式接口

