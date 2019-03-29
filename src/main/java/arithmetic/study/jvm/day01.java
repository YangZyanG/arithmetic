package arithmetic.study.jvm;


import org.junit.Test;

/***
 * java类的加载机制
 */
public class day01 {

    /***
     * 什么是类的加载机制
     * 类的加载是指将类的.class文件中的二进制数据读入到内存中，将其放在方法区内，一个.class文件在方法区中只保存一份。
     * 二进制数据包括静态变量、常量、运行时常量池、类的字段信息、类的方法信息。其中所有堆中的对象共用一套二进制数据。
     * 程序员要用.class文件对应的对象时，会在堆中创建一个该class的对象提供给程序员使用。
     * 即.class就加载在方法区中（内存中有且只有一份），对象放在堆中。
     *
     * 冷知识点
     * 类加载器并不需要等到某个类被"首次主动使用"时再加载它，JVM规范允许类加载器在预料某个类将要被使用时就预先加载它。
     * 如果在预先加载中遇到了.class文件缺失或存在错误，类加载器必须在程序首次使用该类时才报告错误。
     * 如果这个类一直没有被程序主动使用，那么类加载器就不会报告错误。
     */

    /***
     * 类的生命周期
     * 其中类加载的过程包括了加载、验证、准备、解析、初始化五个阶段。
     * 在这五个阶段中，加载、验证、准备、初始化这四个阶段发生的顺序是确定的，而解析阶段则不一定，它在某些情况下可以在初始化阶段之后才开始，这是为了支持java语言的运行时绑定。
     * 另外注意这里的几个阶段是按顺序开始，而不是按顺序进行或完成，因为这些阶段通常都是互相交叉地混合进行的，通常在一个阶段执行的过程中调用或激活另一个阶段。
     *
     * 加载
     * 加载是类加载过程中的第一个阶段，在加载阶段，虚拟机要完成以下三件事
     * 1.通过一个类的全限定名来获取其定义的二进制字节流
     * 2.将这个字节流所代表的静态储存结构转化为方法区的运行时数据结构
     * 3.在Java堆中生成一个代表这个类的java.lang.Class对象，作为对方法区中这些运行时数据的访问入口
     *
     * 验证
     * 确保被加载类的正确性
     *
     * 准备
     * 为类的静态变量分配内存，并将其初始化为默认值
     * 1.这时候进行内存分配的仅包括static关键字的变量，实例变量（对象中的非static变量）会在对象实例化时随对象一起分配到Java堆中
     * 2.这里初始化的默认值即0、null、false等
     * 如：public static int i = 2;
     * 此刻i的值初始化为0，而不是2，赋值为2的过程在初始化阶段进行
     * 但如果i是常量，即带有final关键字，那么在此刻i就会被直接赋值为3
     *
     * 解析
     * 把类中的符号引用转换为直接引用
     *
     * 初始化
     * 为类的静态变量赋予正确的初始值，JVM负责对类进行初始化，主要对变量进行初始化。
     *
     * JVM初始化步骤
     * 1.假如这个类还没有被加载和连接，则程序先加载并连接该类
     * 2.假如该类的直接父类还没有被初始化，则先初始化其直接父类
     * 3.假如类中有初始化语句，则系统依次执行这些初始化语句
     *
     * 类的初始化时机
     * 只有当对类主动使用的时候才会导致类的初始化，一共有以下6种情况
     * 1.创建类的实例，也就是new的方式
     * 2.访问某个类或接口的静态变量，或者对该静态变量赋值
     * 3.调用类的静态方法
     * 4.反射
     * 5.初始化某个类的子类，其父类也会被初始化
     * 6.带有main方法的类或启动类
     *
     * 结束生命周期
     * 在以下几种情况，Java虚拟机将结束生命周期
     * 1.执行了System.exit()方法
     * 2.程序正常执行结束
     * 3.程序在执行过程中遇到了异常或错误而终止
     * 4.由于操作系统错误导致程序终止
     */

    /***
     * 类加载器
     * 下面小例子的输出结果：
     * sun.misc.Launcher$AppClassLoader@18b4aac2
     * sun.misc.Launcher$ExtClassLoader@12edcd21
     * null
     * 从上面的结果可以看出，并没有获取到ExtClassLoader的父loader，原因是BootstrapLoader使用C语言实现的，找不到一个确定的返回父loader的方式，于是就返回null
     *
     * 站在开发人员的角度来看，类加载器可以大致划分为以下三类
     * 1.启动类加载器Bootstrap ClassLoader
     * 负责加载存放在JAVA_HOME/jre/lib下，或被-Xbootclasspath参数指定的路径中的，并且能被虚拟机识别的类库，启动类加载器是无法被Java程序直接引用的
     * 2.扩展类加载器Extension ClassLoader
     * 负责加载存放在JAVA_HOME/jre/lib/ext下，或者由java.ext.dirs系统变量指定的路径中的所有类库，开发者可以直接使用扩展器加载类
     * 3.应用程序类加载器Application ClassLoader
     * 负责加载用户类路径（classpath）所指定的类，开发者可以直接使用该类加载器，如果应用程序中没有定义自己的类加载器，一般情况下默认就是用这个类加载器
     *
     * JVM加载机制
     * 1.全盘负责，当一个类加载器负责加载某个class时，该class所依赖和引用的其他class也将有该类加载器负责加载，除非显示使用另一个类加载器来载入
     * 2.父类委托，先让父类加载器试图加载该类，只有在父类加载器无法加载该类时才尝试从自己的类路径中加载该类
     * 3.缓存机制，缓存机制将会保证所有加载过的class都会被缓存，当程序中需要使用某个class时，类加载器先从缓存区寻找该class，只有缓存区不存在时，
     * 系统才会读取该类对应的二进制数据，并将其转换成class对象存入缓存区，这就是为什么修改了class之后需要重启JVM才能生效。
     */
    @Test
    public void method1(){
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        System.out.println(classLoader);
        System.out.println(classLoader.getParent());
        System.out.println(classLoader.getParent().getParent());
    }


    /***
     * 类的加载
     * 类的加载有三种方式
     * 1.命令行启动时由JVM初始化加载
     * 2.通过Class.forName()方法动态加载
     * 3.通过ClassLoader.loadClass()方法动态加载
     *
     * Class.forName()和ClassLoader.loadClass()区别
     * Class.forName()：将类的.class文件加载到jvm中之外，还会对类进行解释，执行类中的static块
     * ClassLoader.loadClass()：只干一件事情，就是将.class文件加载到jvm中，不会执行static中的内容,只有在newInstance才会去执行static块
     */
    @Test
    public void method2() throws ClassNotFoundException {
        ClassLoader classLoader = day01.class.getClassLoader();
        System.out.println(classLoader);
        //使用ClassLoader.loadClass()来加载类，不会执行静态块
        classLoader.loadClass("arithmetic.study.jvm.day01");
        //使用Class.forName()来加载类，默认会执行静态块
        Class.forName("arithmetic.study.jvm.day01");
        //Class.forName(name, initialize, loader)带参函数也可控制是否加载static块。
        Class.forName("arithmetic.study.jvm.day01", false, this.getClass().getClassLoader());
    }

    static {
        System.out.println("静态块初始化了！");
    }

    /***
     * 双亲委派模式
     * 双亲委派的工作流程是：
     * 如果一个类加载器收到了类加载的请求，它首先不会自己去尝试加载这个类，而是把请求委托给父加载器去完成，依次向上。
     * 因此，所有的类加载请求最终都应该被传递到顶层的启动类加载器，只有当父类加载器在它的搜索范围内没有找到所需的类时，子加载器才会尝试自己去加载该类。
     *
     * 双亲委派机制
     * 1.当AppClassLoader加载一个class时，它首先不会尝试自己去加载，而是把类加载请求委托给父类加载器ExtClassLoader去完成
     * 2.当ExtClassLoader加载一个class时，它首先不会尝试自己去加载，而是把类加载请求委托给父类加载器BootstrapClassLoader去完成
     * 3.如果BootstrapClassLoader加载失败（例如在JAVA_HOME/jre/lib中未查找到该类），会使用ExtClassLoader来尝试加载
     * 4.若ExtClassLoader加载失败（例如在JAVA_HOME/jre/lib/ext中未查找到该类），会使用AppClassLoader来尝试加载
     * 5.若AppClassLoader加载失败，抛出异常ClassNotFoundException
     *
     * 双亲委派模式的意义
     * 1.防止内存中出现多份同样的字节码
     * 2.保证Java程序安全稳定运行
     */

    /***
     * 自定义类加载器
     * 通常情况下，我们都是直接使用系统类加载器。但是，有的时候，我们也需要自定义类加载器。
     * 比如应用是通过网络来传输 Java 类的字节码，为保证安全性，这些字节码经过了加密处理，这时系统类加载器就无法对其进行加载，这样则需要自定义类加载器来实现。
     * 自定义类加载器一般都是继承自 ClassLoader 类，一般情况下，我们只需要重写 findClass 方法即可。
     */
}
