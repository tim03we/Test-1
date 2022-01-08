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
import cn.nukkit.command.CommandSender;
import tim03we.futureplots.FuturePlots;
import tim03we.futureplots.commands.BaseCommand;
import tim03we.futureplots.utils.Plot;
import tim03we.futureplots.utils.PlotPlayer;

public class InfoCommand extends BaseCommand {

    public InfoCommand(String name, String description, String usage) {
        super(name, description, usage);
    }

    @Override
    public void execute(CommandSender sender, String command, String[] args) {
        if(sender instanceof Player) {
            Plot plot = new PlotPlayer((Player) sender).getPlot();
            if(plot != null) {
                sender.sendMessage(translate(false, "plot.info.title"));
                if(FuturePlots.provider.hasOwner(plot)) {
                    sender.sendMessage(translate(false, "plot.info.text", FuturePlots.provider.getOwner(plot), plot.getX() + ";" + plot.getZ(), FuturePlots.provider.getHelpers(plot).toString(), FuturePlots.provider.getDenied(plot).toString(), FuturePlots.provider.getMembers(plot).toString()));
                } else {
                    sender.sendMessage(translate(false, "plot.info.text", "[]", "[]", "[]", "[]", "[]"));

                }
            } else {
                sender.sendMessage(translate(true, "not.in.plot"));
            }
        }
    }
}
