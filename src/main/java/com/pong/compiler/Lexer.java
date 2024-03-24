package com.pong.compiler;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Lexer {
    public static class Token {
        public final int direction;
        public final int steps;

        public Token(int direction, int steps) {
            this.direction = direction;
            this.steps = steps;
        }

        @Override
        public String toString() {
            return String.format("Token[direction=%d, steps=%d]", direction, steps);
        }
    }

    public List<Token> tokenize(String filePath) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filePath));
        List<Token> tokens = new ArrayList<>();

        for (String line : lines) {
            String[] parts = line.split(" ");
            if (parts.length == 2 && parts[0].equalsIgnoreCase("move")) {
                int direction;
                int steps;
                try {
                    direction = Integer.parseInt(parts[1]);
                    steps = 1; // Assumption: all movements have a single step
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid direction or step count: " + e.getMessage());
                }
                tokens.add(new Token(direction, steps));
            } else {
                throw new IllegalArgumentException("Invalid command format: " + line);
            }
        }
        return tokens;
    }
}
