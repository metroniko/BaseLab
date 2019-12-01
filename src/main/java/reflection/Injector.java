package reflection;

import com.database.sort.ISorted;
import ru.vsu.lab.repository.IRepository;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Properties;

/**
 * класс который реализует метод записси в вопе объекта класса сортировки.
 * @param <T> параметр типизации.
 */
public class Injector<T> {
    /**
     * метод для произведения иньекции.
     * @param rep объект класса репозитория в поля которого будут проверяться с
     *            помощью рефлексии.
     * @param <T> параметр типизации.
     * @throws IOException ошибка при необнаружении файла .properties.
     */
    public static <T> void inject(IRepository<T> rep) throws IOException {
        String propsPath = "src\\main\\resources\\props.properties";
        Class<?> clazz =  rep.getClass();
        Field[] fields = clazz.getDeclaredFields();
        Properties props = new Properties();
        props.load(new FileInputStream(propsPath));
        for (Field field: fields) {
            System.out.println(field.getClass().getName());
            if (field.isAnnotationPresent(LabInject.class)) {
                try {
                    Class<?> seatedClass =  Class
                            .forName(
                            props.getProperty(
                                    ISorted.class.getName())
                            );

                    Object seatedClassObject = seatedClass.newInstance();
                    field.setAccessible(true);
                    field.set(rep, seatedClassObject);
                } catch (IllegalAccessException |
                        ClassNotFoundException |
                        InstantiationException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
