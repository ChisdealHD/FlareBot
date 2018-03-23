package stream.flarebot.flarebot.commands.secret.internal;

import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import stream.flarebot.flarebot.FlareBot;
import stream.flarebot.flarebot.Getters;
import stream.flarebot.flarebot.commands.CommandType;
import stream.flarebot.flarebot.commands.InternalCommand;
import stream.flarebot.flarebot.objects.GuildWrapper;
import stream.flarebot.flarebot.permissions.PerGuildPermissions;
import stream.flarebot.flarebot.util.MessageUtils;

public class ShardRestartCommand implements InternalCommand {

    @Override
    public void onCommand(User sender, GuildWrapper guild, TextChannel channel, Message message, String[] args, Member member) {
        if (PerGuildPermissions.isStaff(sender)) {
            int shard = Integer.parseInt(args[0]);
            if (shard >= 0 && shard < Getters.getShards().size()) {
                FlareBot.instance().getShardManager().restart(shard);
                MessageUtils.sendSuccessMessage("Restarting shard " + shard, channel);
            } else
                MessageUtils.sendErrorMessage("Invalid shard ID!", channel);
        }
    }

    @Override
    public String getCommand() {
        return "restart";
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public String getUsage() {
        return "{%}restart <shard>";
    }

    @Override
    public CommandType getType() {
        return CommandType.INTERNAL;
    }

    @Override
    public boolean isDefaultPermission() {
        return false;
    }
}