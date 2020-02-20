
package io.github.paulanthonyreitz.reitzmmo.Scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.*;

public class HP_Scoreboard implements Listener {
    @EventHandler
    public void onPlayerJoinSetBoard(PlayerJoinEvent e) {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();
        Objective objective = board.registerNewObjective("showhealth", "health");
        Team team = board.registerNewTeam("Humans");
        team.addPlayer(e.getPlayer());
        objective.setDisplaySlot(DisplaySlot.BELOW_NAME);
        objective.setDisplayName("/ 20");
        for (Player online : Bukkit.getOnlinePlayers()) {
            online.setScoreboard(board);
            online.setHealth(online.getHealth());
            if(online.hasMetadata("NPC"))
            {
                online.getScoreboard().resetScores("showhealth");
            }
        }
    }

    public static void setHPScoreboard(Player p) {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();
        Objective objective = board.registerNewObjective("showhealth", "health");
        Team team = board.registerNewTeam("Humans");
        team.addPlayer(p);
        objective.setDisplaySlot(DisplaySlot.BELOW_NAME);
        objective.setDisplayName("/ 20");
        for (Player online : Bukkit.getOnlinePlayers()) {
            online.setScoreboard(board);
            online.setHealth(online.getHealth());
        }
    }
}
