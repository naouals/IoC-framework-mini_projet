package metier;
import annotations.Autowired;
import annotations.Component;
import dao.DaoImpl;

@Component
public class MetierImpl implements IMetier{
    @Autowired
    private DaoImpl dao;

    public double calcul() {
        return dao.getData() * 2;
    }
    public void setDao(DaoImpl dao) {
        this.dao = dao;
    }

}
