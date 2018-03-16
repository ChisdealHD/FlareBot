package stream.flarebot.flarebot.util.general;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import stream.flarebot.flarebot.objects.GuildWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class VariableUtils {

    /**
     * This method is used to parse variables in a message, this means that we can allow users to pass things into
     * their command messages, things like usernames, mentions and channel names to name a few.
     *
     * Variable list:
     * <h2>User Variables</h2>
     * `{user}` - User's name.
     * `{username}` - User's name.
     * `{nickname}` - User's nickname for a guild or their username if the guild is not passed or they're not a member.
     * `{tag}` - User's name and discrim in tag format - Username#discrim.
     * `{mention}` - User mention.
     * `{user_id}` - User's ID.
     *
     * <hr />
     *
     * <h2>Guild Variables</h2>
     * `{guild}` - Guild name.
     * `{region}` - Guild region (Pretty name - Amsterdam, Amsterdam (VIP)).
     * `{members}` - Total guild members.
     * `{owner}` - Guild owner's name.
     * `{owner_id}` - Guild owner's ID.
     * `{owner_mention}` - Guild owner mention.
     *
     * <hr />
     *
     * <h2>Channel Variables</h2>
     * `{channel}` - Channel name.
     * `{channel_mention}` - Channel mention/
     * `{topic}` - Channel topic.
     * `{category}` - Category name or 'no category'
     *
     * @return The parsed message
     */
    public static String parseVariables(@Nonnull String message,
                                        @Nullable GuildWrapper wrapper,
                                        @Nullable TextChannel channel,
                                        @Nullable User user) {
        @Nullable
        Guild guild = null;
        @Nullable
        Member member = null;
        if (wrapper != null) {
            guild = wrapper.getGuild();
            if (user != null)
                member = guild.getMember(user);
        }

        String parsed = message;
        // User variables
        if (user != null) {
            parsed = parsed
                    .replace("{user}", user.getName())
                    .replace("{username}", user.getName())
                    .replace("{nickname}", member == null ? user.getName() : member.getEffectiveName())
                    .replace("{tag}", user.getName() + "#" + user.getDiscriminator())
                    .replace("{mention}", user.getAsMention())
                    .replace("{user_id}", user.getId());
        }
        // Guild variables
        if (guild != null) {
            parsed = parsed
                    .replace("{guild}", guild.getName())
                    .replace("{region}", guild.getRegion().getName())
                    .replace("{members}", String.valueOf(guild.getMemberCache().size()))
                    .replace("{owner}", guild.getOwner().getEffectiveName())
                    .replace("{owner_id}", guild.getOwner().getUser().getId())
                    .replace("{owner_mention}", guild.getOwner().getUser().getAsMention());
        }
        // Channel variables
        if (channel != null) {
            parsed = parsed
                    .replace("{channel}", channel.getName())
                    .replace("{channel_mention}", channel.getAsMention())
                    .replace("{topic}", channel.getTopic())
                    .replace("{category}", (channel.getParent() != null ? channel.getParent().getName() : "no category"));
        }

        return parsed;
    }
}