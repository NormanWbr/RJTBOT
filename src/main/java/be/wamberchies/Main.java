package be.wamberchies;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.emoji.KnownCustomEmoji;
import org.javacord.api.entity.permission.Role;
import org.javacord.api.entity.user.User;

import java.util.List;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {

        //LOGIN
        DiscordApi api = new DiscordApiBuilder().setToken("").setAllIntents().login().join();

        System.out.println("Logged in as " + api.getYourself().getName() + "#" + api.getYourself().getDiscriminator());

        //USER ID
        final Long VANLENTIN = 356541379960242186L;
        final Long HENRY = 1028972171583823912L;
        final Long NORMAN = 166253668063117312L;
        final Long BENJAMIN = 390224464643883009L;
        final Long MAXIME = 261090059631984641L;
        final Long TIME = 973215637059010600L;
        final Long JEANMICHEL = 417768542335795231L;
        final Long DANIYAL = 294550667761025024L;
        final Long JEROME = 476488912517333009L;
        final Long JULIEN = 950675620852019200L;
        final Long SIMON = 460749625083691009L;
        final Long CHARLOTTE = 697478150040453130L;
        final Long JULIE = 249120430806401024L;

        //EMOJI
        Optional<KnownCustomEmoji> emojiJu = api.getCustomEmojisByName("ju").stream().findFirst();
        Optional<KnownCustomEmoji> emojiMaitrepiece = api.getCustomEmojisByName("maitrepiece").stream().findFirst();

        //LISTENER MESSAGE
        api.addMessageCreateListener(event -> {

            User user = event.getMessageAuthor().asUser().get();

            List<Role> roles = event.getMessageAuthor().asUser().get().getRoles(event.getServer().get());

            Role formateur = event.getServer().get().getRolesByName("Formateur").get(0);

            //SI userId = julieId ALORS reactionJu
            if (user.getId() == JULIE) {
                event.getMessage().addReaction(emojiJu.get());
                System.out.println("Emoji \"ju\" ajouté");
            }
            //SI userId = benjaminId ALORS reactionMaitrepiece
            if (user.getId() == BENJAMIN) {
                event.getMessage().addReaction(emojiMaitrepiece.get());
                System.out.println("Emoji \"maitrepiece\" ajouté");
            }
            //SI userId = julieId OU userId = charlotteId ALORS reactionCafe
            if (user.getId() == JULIE || user.getId() == CHARLOTTE) {
                event.getMessage().addReaction("☕");
                System.out.println("Emoji \"coffee\" ajouté");
            }

            //SI message CONTIENT "vscode" ALORS delete
            if (event.getMessageContent().contains("vscode") && !roles.contains(formateur)) {
                event.getMessage().delete();
                System.out.println("Message supprimé");
                event.getMessageAuthor().getRoleColor();
            }

        });

    }
}
