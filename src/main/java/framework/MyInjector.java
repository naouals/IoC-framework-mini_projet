package framework;

import annotations.Autowired;
import annotations.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class MyInjector {
    private Map<String, Object> container = new HashMap<>();

    // Charger les beans depuis un fichier XML
    public static Object loadFromXML(String fileName) {
        try {
            JAXBContext context = JAXBContext.newInstance(Beans.class);
            InputStream inputStream = MyInjector.class.getClassLoader().getResourceAsStream(fileName);
            if (inputStream == null) {
                throw new RuntimeException("Fichier XML introuvable : " + fileName);
            }
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Beans beans = (Beans) unmarshaller.unmarshal(inputStream);

            Map<String, Object> container = new HashMap<>();

            // Instanciation des beans
            for (Bean bean : beans.getBeanList()) {
                Class<?> clazz = Class.forName(bean.getClassName());
                Object instance = clazz.getDeclaredConstructor().newInstance();
                container.put(bean.getId(), instance);
            }

            // Injection par setter (simple version)
            for (Bean bean : beans.getBeanList()) {
                Object instance = container.get(bean.getId());
                Class<?> clazz = instance.getClass();

                // Trouver les propriétés à injecter (si besoin tu ajoutes un <property> dans config.xml)
                if (bean.getId().equals("metier")) {
                    Method setter = clazz.getMethod("setDao", dao.DaoImpl.class);
                    setter.invoke(instance, container.get("dao"));
                }
            }

            return container.get("metier");

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    // Charger les beans via les annotations
    public void loadFromAnnotations(Class<?>... classes) throws Exception {
        // Création des instances et ajout au conteneur
        for (Class<?> clazz : classes) {
            if (clazz.isAnnotationPresent(Component.class)) {
                Object instance = clazz.getDeclaredConstructor().newInstance();
                container.put(clazz.getSimpleName(), instance);
            }
        }

        // Injection des dépendances via @Autowired
        for (Object bean : container.values()) {
            for (Field field : bean.getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(Autowired.class)) {
                    field.setAccessible(true);
                    Object dep = container.get(field.getType().getSimpleName());
                    field.set(bean, dep);
                }
            }
        }
    }

    // Récupérer un bean par son nom
    public Object getBean(String name) {
        return container.get(name);
    }

    // Méthode d'aide pour capitaliser le nom d'une classe (si nécessaire)
    private String capitalize(String name) {
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }
}
