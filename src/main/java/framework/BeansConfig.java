package framework;
import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "beans")
public class BeansConfig {
    @XmlElement(name = "bean")
    public List<Bean> beans;
}
