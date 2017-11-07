
package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class StatisticsTest {
    
        Reader readerStub = new Reader() {
            
            @Override
            public List<Player> getPlayers() {
                ArrayList<Player> players = new ArrayList();
                
                players.add(new Player("Semenko", "EDM", 4, 12));
                players.add(new Player("Lemieux", "PIT", 45, 54));
                players.add(new Player("Kurri", "EDM", 37, 53));
                players.add(new Player("Yzerman", "DET", 42, 56));
                players.add(new Player("Gretzky", "EDM", 35, 89));

                return players;
            }
        };
        
    
    Statistics stats;
        @Before
        public void setUp() {
            
        stats = new Statistics(readerStub);
    }
        
        
        @Test
        public void playerTestReturnsCorrect() {
            Player p = stats.search("Semenko");
            Assert.assertEquals("Semenko", p.getName());
        }
        
        @Test
        public void wrongPlayerTestReturnsNull() {
            Player p = stats.search("Laitinen");
            Assert.assertNull(p);
        }
        
        @Test
        public void playersOfTeamCorrect() {
            List<Player> players = stats.team("PIT");
        
            Assert.assertEquals("Lemieux", players.get(0).getName());
        }
        
        @Test
        public void correctAmountOfTopScorers() {
            List<Player> scorers = stats.topScorers(1);
            Assert.assertEquals("Gretzky", scorers.get(0).getName());
        }
        
        @Test
        public void correctAmountOfTopScorersMoreThanOne() {
            List<Player> scorers = stats.topScorers(2);
            Assert.assertEquals("Gretzky", scorers.get(0).getName());
            Assert.assertEquals("Lemieux", scorers.get(1).getName());
        }
    
}    
  
