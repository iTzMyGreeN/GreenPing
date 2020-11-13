package hu.itzmygreen.greenping.commands;

import hu.itzmygreen.greenping.GreenPing;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;


public class PingCommand extends Command {
    private GreenPing plugin;

    public PingCommand(GreenPing plugin) {
        super("ping");
        this.plugin = plugin;
    }

    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage("GreenScrims | A parancs konzolból nem futtatható le!");
            return;
        }

        ProxiedPlayer p = (ProxiedPlayer) sender;
        if (args.length == 0) {
        	
            p.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    plugin.getConfig().getString("ping-parancs.ping-uzenet").replace("%ping%", "" + p.getPing())));
        } else {

            String target = args.length > 0 ? args[0] : null;
            ProxiedPlayer targetP = plugin.getProxy().getPlayer(target);
            if (targetP == null) {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ping-parancs.jatekos-nem-talalhato")));
                return;
            }
            p.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    plugin.getConfig().getString("ping-parancs.ping-jatekos-uzenet")
                            .replace("%ping%", "" + targetP.getPing())
                            .replace("%target%", targetP.getName())));
        }
    }

}