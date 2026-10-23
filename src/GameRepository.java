import java.util.ArrayList;
import java.util.List;


public interface GameRepository
{
    Game add(Game game);
    List<Game> findAll();
    Game findById(int id);


}
