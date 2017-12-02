package stream.flarebot.flarebot.mod;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.User;
import org.apache.commons.lang3.text.WordUtils;
import stream.flarebot.flarebot.util.MessageUtils;

import java.awt.Color;

public enum ModlogEvent {

    MEMBER_JOIN("MEMBER_JOIN", "Triggers when user joins the server.", Color.GREEN),
    MEMBER_LEAVE("MEMBER_LEAVE", "Triggers when user leaves the server.", Color.RED),

    MEMBER_ROLE_GIVE("MEMBER_ROLE_GIVE", "Triggers when a role is added to a user.", Color.GREEN),
    MEMBER_ROLE_REMOVE("MEMBER_ROLE_REMOVE", "Triggers when a role is taken away a user.", Color.RED),

    MEMBER_VOICE_JOIN("MEMBER_VOICE_JOIN", "Triggers when a user joins a voice channel.", Color.GREEN),
    MEMBER_VOICE_LEAVE("MEMBER_VOICE_LEAVE", "Triggers when a user leaves a voice channel.", Color.RED),
    MEMBER_VOICE_MOVE("MEMBER_VOICE_MOVE", "Triggers when a user moves to a different voice channel. Either because they moved or because they where force moved.", Color.decode("#addfe6")),

    MEMBER_NICK_CHANGE("MEMBER_NICK_CHANGE", "Triggers when a user changes their nick.", Color.decode("#addfe6")),

    ROLE_CREATE("ROLE_CREATE", "Triggers when a role is created.", Color.GREEN),
    ROLE_DELETE("ROLE_DELETE", "Triggers when a role is deleted.", Color.RED),
    ROLE_EDIT("ROLE_EDIT", "Triggers when a role is edited.", Color.decode("#addfe6")),

    CHANNEL_CREATE("CHANNEL_CREATE", "Triggers when a channel is created.", Color.GREEN),
    CHANNEL_DELETE("CHANNEL_DELETE", "Triggers when a channel is deleted.", Color.RED),

    MESSAGE_EDIT("MESSAGE_EDIT", "Triggers when a message is edited.", Color.decode("#addfe6")),
    MESSAGE_DELETE("MESSAGE_DELETE", "Triggers when a message is deleted.", Color.RED),

    GUILD_EXPLICIT_FILTER_CHANGE("GUILD_EXPLICIT_FILTER_CHANGE", "Triggers when the server's explicit filter level is changed.", Color.orange),
    GUILD_UPDATE("GUILD_UPDATE", "Triggers when any of the server setting are changed.", Color.decode("#addfe6")),

    FLAREBOT_AUTOASSIGN_ROLE("FLAREBOT_AUTOASSIGN_ROLE", "Triggers when a role is automatically given to a user", Color.GREEN),
    FLAREBOT_COMMAND("FLAREBOT_COMMAND", "Triggers when a user runs a **FlareBot** command.", Color.decode("#addfe6"));

    private String title;
    private String description;
    private Color color;

    ModlogEvent(String title, String description, Color color) {
        this.title = title;
        this.description = description;
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public Color getColor() {
        return color;
    }

    public EmbedBuilder getEventEmbed(User user, User responsible) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle(WordUtils.capitalize(getTitle().toLowerCase().replaceAll("_", " ")));
        if (user != null) {
            eb.addField("User", MessageUtils.getTag(user) + " (" + user.getId() + ")", true);
        }
        if (responsible != null) {
            eb.addField("Responsible", responsible.getAsMention(), true);
        }
        eb.setColor(color);
        return eb;
    }

    public String getTitle() {
        return title;
    }
}
