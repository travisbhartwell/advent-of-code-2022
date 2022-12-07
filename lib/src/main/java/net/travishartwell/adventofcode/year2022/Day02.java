package net.travishartwell.adventofcode.year2022;

import java.util.List;

public class Day02 {
    public static Integer solvePart1(List<String> input) {
        int totalScore = 0;

        for (String line : input) {
            final Shape opponentPlay = Shape.parseOpponentPlay(line.charAt(0));
            final Shape youPlay = Shape.parseYouPlay(line.charAt(2));

            totalScore += youPlay.scoreAgainst(opponentPlay);
        }

        return totalScore;
    }

    public static Integer solvePart2(List<String> input) {
        int totalScore = 0;

        for (String line : input) {
            final Shape opponentPlay = Shape.parseOpponentPlay(line.charAt(0));
            final RoundResult roundResult = RoundResult.parseResultCode(line.charAt(2));
            final Shape youPlay = opponentPlay.youPlayForResult(roundResult);

            totalScore += youPlay.scoreAgainst(opponentPlay);
        }

        return totalScore;
    }

    enum RoundResult {
        Lose('X', 0), Draw('Y', 3), Win('Z', 6);

        private final char resultCode;
        private final int score;


        RoundResult(final char resultCode, final int score) {
            this.resultCode = resultCode;
            this.score = score;
        }

        static RoundResult parseResultCode(final char resultCode) {
            for (RoundResult roundResult : RoundResult.values()) {
                if (roundResult.getResultCode() == resultCode) {
                    return roundResult;
                }
            }

            throw new IllegalArgumentException("Invalid opponent char: " + resultCode);
        }

        public int getScore() {
            return score;
        }

        public char getResultCode() {
            return resultCode;
        }
    }

    enum Shape {
        Rock('A', 'X', 1), Paper('B', 'Y', 2), Scissors('C', 'Z', 3);

        private final char opponentPlay;
        private final char youPlay;

        private final int score;

        Shape(final char opponentPlay, final char youPlay, final int score) {
            this.opponentPlay = opponentPlay;
            this.youPlay = youPlay;
            this.score = score;
        }

        static Shape parseOpponentPlay(final char opponentPlay) {
            for (Shape shape : Shape.values()) {
                if (shape.getOpponentPlay() == opponentPlay) {
                    return shape;
                }
            }

            throw new IllegalArgumentException("Invalid opponent char: " + opponentPlay);
        }

        static Shape parseYouPlay(final char youPlay) {
            for (Shape shape : Shape.values()) {
                if (shape.getYouPlay() == youPlay) {
                    return shape;
                }
            }

            throw new IllegalArgumentException("Invalid opponent char: " + youPlay);
        }

        public char getOpponentPlay() {
            return opponentPlay;
        }

        public char getYouPlay() {
            return youPlay;
        }

        public int getScore() {
            return score;
        }

        Shape youPlayForResult(final RoundResult roundResult) {
            return switch (roundResult) {
                case Lose -> winsOver();
                case Draw -> this;
                case Win -> losesTo();
            };
        }

        Shape losesTo() {
            return switch (this) {
                case Rock -> Paper;
                case Paper -> Scissors;
                case Scissors -> Rock;
            };
        }

        Shape winsOver() {
            return switch (this) {
                case Rock -> Scissors;
                case Paper -> Rock;
                case Scissors -> Paper;
            };
        }

        RoundResult resultAgainst(final Shape opponentShape) {
            if (opponentShape == losesTo()) {
                return RoundResult.Lose;
            } else if (opponentShape == winsOver()) {
                return RoundResult.Win;
            } else {
                return RoundResult.Draw;
            }
        }

        public int scoreAgainst(final Shape opponentShape) {
            final RoundResult roundResult = resultAgainst(opponentShape);

            return roundResult.getScore() + getScore();
        }
    }
}
