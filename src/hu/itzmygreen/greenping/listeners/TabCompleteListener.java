package hu.itzmygreen.greenping.listeners;

import hu.itzmygreen.greenping.GreenPing;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.TabCompleteEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.md_5.bungee.event.EventPriority;


public class TabCompleteListener implements Listener {
    private GreenPing plugin;

    public TabCompleteListener(GreenPing plugin) {
        this.plugin = plugin;
    }

    /**
     * @author https://github.com/Sabbertran
     */
    @EventHandler(priority = EventPriority.NORMAL)
    public void onTabComplete(TabCompleteEvent ev) {
        String partialPlayerName = ev.getCursor().toLowerCase();

        int lastSpaceIndex = partialPlayerName.lastIndexOf(' ');
        if (lastSpaceIndex >= 0) {
            partialPlayerName = partialPlayerName.substring(lastSpaceIndex + 1);
        }

        for (ProxiedPlayer p : plugin.getProxy().getPlayers()) {
            if (p.getName().toLowerCase().startsWith(partialPlayerName)) {
                ev.getSuggestions().add(p.getName());
            }
        }
    }
}