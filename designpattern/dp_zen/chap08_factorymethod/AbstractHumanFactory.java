package dp_zen.chap08_factorymethod;

/**
 * description:
 *
 * @author liyazhou
 * @since 2017-06-28 11:01
 */
public abstract class AbstractHumanFactory {

    public abstract <T extends Human> T createHuman(Class<T> clazz);

}
