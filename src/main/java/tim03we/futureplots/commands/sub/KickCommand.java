package tim03we.futureplots.commands.sub;

/*
 * This software is distributed under "GNU General Public License v3.0".
 * This license allows you to use it and/or modify it but you are not at
 * all allowed to sell this plugin at any cost. If found doing so the
 * necessary action required would be taken.
 *
 * FuturePlots is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License v3.0 for more details.
 *
 * You should have received a copy of the GNU General Public License v3.0
 * along with this program. If not, see
 * <https://opensource.org/licenses/GPL-3.0>.
 */

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.CommandSender;
import tim03we.futureplots.commands.BaseCommand;
import tim03we.futureplots.utils.Plot;
import tim03we.futureplots.utils.PlotPlayer;

public class KickCommand extends BaseCommand {

    public KickCommand(String name, String description, String usage) {
        super(name, description, usage);
    }

    @Override
    public void execute(CommandSender sender, String command, String[] args) {
        if(sender instanceof Player) {
            Plot plot = new PlotPlayer((Player) sender).getPlot();
            if(plot != null) {
                if(plot.canByPass((Player) sender)) {
                    if(args.length > 1) {
                        Player target = Server.getInstance().getPlayer(args[1]);
                        if(target != null) {
                            Plot tpp = new PlotPlayer(target).getPlot();
                            if(tpp != null && tpp.getX() == plot.getX() && tpp.getZ() == plot.getZ() && tpp.getLevelName().equals(plot.getLevelName())) {
                                if (target.hasPermission("plot.kick.bypass")) {
                                    sender.sendMessage(translate(true, "plot.kick.bypass"));
                                } else {
                                    sender.sendMessage(translate(true, "plot.kick", target.getName()));
                                    target.sendMessage(translate(true, "plot.kick.target"));
                                    target.teleport(Server.getInstance().getDefaultLevel().getSafeSpawn());
                                }
                            } else {
                                sender.sendMessage(translate(true, "plot.kick.error"));
                            }
                        } else {
                            sender.sendMessage(translate(true, "player.not.found"));
                        }
                    } else {
                        sender.sendMessage(getUsage());
                    }
                } else {
                    sender.sendMessage(translate(true, "not.a.owner"));
                }
            } else {
                sender.sendMessage(translate(true, "not.in.plot"));
            }
        }
    }
}