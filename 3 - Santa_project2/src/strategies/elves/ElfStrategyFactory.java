package strategies.elves;

import enums.ElvesType;

public final class ElfStrategyFactory {
    private ElfStrategyFactory() {
        // constructor for checkstyle
    }

    /**
     * Creates a new strategy based on the elf type.
     * @param elfType given
     * @return the correct strategy to be used
     */
    public static ElfStrategy createStrategy(final ElvesType elfType) {
        switch (elfType) {
            case YELLOW: return new YellowElfStrategy();
            case PINK:   return new PinkElfStrategy();
            case BLACK:  return new BlackElfStrategy();
            default:     throw  new IllegalArgumentException("The elf does not have a strategy.");
        }
    }
}
