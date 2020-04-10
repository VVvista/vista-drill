package com.vista.design.drill.decorator.regulardecorator;

/**
 * 修饰模式：
 * 在不改变原类文件或使用继承的情况下，动态的扩展一个对象的功能。
 * 通过创建一个包装对象，即修饰包裹真实的对象
 * 1.创建抽象构建角色person；
 * 2.创建具体的构建角色men：继承抽象构建角色
 * 3.创建修饰角色Decorator：继承抽象构建角色，并持有抽象构建角色对象示例
 * 4.创建具体的修饰角色ProgrammerDecorator：给构建对象添加额外的修饰功能
 * <p>
 * 优点：在不改变原有构建角色的基础上，通过修饰角色，动态的给构建角色添加额外的修饰功能
 * 可以嵌套或组合各修饰功能，比继承更加灵活
 * 缺点：程序嵌套过多，太复杂
 *
 * @author WenTingTing by 2020/4/10
 */
public class Main {
    public static void main(String[] args) {
        // 调用单个修饰功能
        final Men men = new Men();
        final ProgrammerDecorator programmer = new ProgrammerDecorator(men);
        programmer.dress();// 男士穿着 程序员：T恤-大裤衩-人字拖
        System.out.println();

        final ArtistDecorator artist = new ArtistDecorator(men);
        artist.dress();// 男士穿着 艺术家：引领时尚潮流
        System.out.println();

        // 嵌套组合多个修饰功能
        final ArtistDecorator fashion = new ArtistDecorator(programmer);
        fashion.dress();// 男士穿着 程序员：T恤-大裤衩-人字拖 艺术家：引领时尚潮流
        System.out.println();

        // 具体构建角色被修饰后仍是统一类型
        final Women women = new Women();
        final Person artistDecorator = new ArtistDecorator(women);
        artistDecorator.dress();// 女士穿着 艺术家：引领时尚潮流
        final Person programmerDecorator = new ProgrammerDecorator(artistDecorator);
        programmerDecorator.dress();// 女士穿着  艺术家：引领时尚潮流  程序员：T恤-大裤衩-人字拖


    }

}

