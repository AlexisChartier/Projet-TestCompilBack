package com.pong.compiler;

import com.pong.compiler.Lexer;

import java.util.List;

public class Parser {

    public static class Command {
        public final int direction;
        public final int steps;

        public Command(int direction, int steps) {
            this.direction = direction;
            this.steps = steps;
        }
    }

    public static class Program {
        public final List<Command> commands;

        public Program(List<Command> commands) {
            this.commands = commands;
        }
    }

    public Program parse(List<Lexer.Token> tokens) {
        List<Command> commands = tokens.stream()
                .map(token -> new Command(token.direction, token.steps))
                .toList();
        return new Program(commands);
    }
}
