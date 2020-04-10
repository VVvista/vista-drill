package com.vista.design.drill.decorator.regulardecorator;

/**
 * 具体修饰角色
 * 需要继承装饰角色，并且附加方法功能
 *
 * @author WenTingTing by 2020/4/10
 */
public class ArtistDecorator extends Decorator {
    public ArtistDecorator(Person person) {
        super(person);
    }

    @Override
    public void dress() {
        super.dress();
        fashion();//在传过来的具体构件角色原有功能的基础上附加的功能
    }

    /**
     * 附加功能
     */
    public void fashion() {
        System.out.println("艺术家：引领时尚潮流");
    }
}
