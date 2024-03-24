package com.pong.compiler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class CodeGenerator {

    private final String className = "PongController";

    public void generateCode(Parser.Program program, String outputPath) throws IOException {
        StringBuilder classContent = new StringBuilder();

        classContent.append("package com.pong.api;\n\n");
        classContent.append("import com.pong.logic.PongLogic;\n");
        classContent.append("import com.pong.api.PongGameData;\n\n");
        classContent.append("public class ").append(className).append(" {\n\n");

        for (int i = 0; i < program.commands.size(); i++) {
            Parser.Command command = program.commands.get(i);
            classContent.append("    public void movePaddle").append(i).append("() {\n");
            switch (command.direction) {
                case 0 ->
                        classContent.append("        PongGameData.getInstance().getPlayer().setMove(-1);\n"); // Up
                case 1 ->
                        classContent.append("        PongGameData.getInstance().getPlayer().setMove(1);\n"); // Down
            }
            classContent.append("        PongLogic.getInstance().update();\n");
            classContent.append("    }\n\n");
        }
        classContent.append("}\n\n");

        Files.writeString(Paths.get(outputPath), classContent.toString(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }
}
