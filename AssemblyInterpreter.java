import java.util.HashMap;
import java.util.Map;

public class AssemblyInterpreter {
    private Map<String, Integer> registers;

    public AssemblyInterpreter() {
        registers = new HashMap<>();
    }

    public void execute(String program) {
        String[] lines = program.split("\n");
        for (String line : lines) {
            line = line.trim();
            if (line.startsWith("MV")) {
                displayMV(line);
            } else if (line.startsWith("ADD")) {
                addRegister(line);
            } else if (line.startsWith("SHOW")) {
                showResult(line);
            }
        }
    }

    private void displayMV(String line) {
        String[] parts = line.split(",");
        String regName = parts[1].trim();
        String valueString = parts[2].trim();
        int value;
        if (valueString.startsWith("#")) {
            value = Integer.parseInt(valueString.substring(1));
        } else {
            value = Integer.parseInt(valueString);
        }
        registers.put(regName, value);
    }

    private void addRegister(String line) {
        String[] parts = line.split(",");
        String regName = parts[1].trim();
        String operand = parts[2].trim();
        int value;
        if (operand.startsWith("REG")) {
            value = registers.get(operand);
        } else {
            value = Integer.parseInt(operand);
        }
        registers.put(regName, registers.get(regName) + value);
    }

    private void showResult(String line) {
        String[] parts = line.split(" ");
        String regName = parts[1].trim();
        System.out.println(regName + ": " + registers.get(regName));
    }

    public static void main(String[] args) {
        String program = "MV, REG1, #2000\n" +
                         "MV, REG2, #4000\n" +
                         "ADD, REG1, REG2\n" +
                         "ADD, REG1, 600\n" +
                         "SHOW, REG1";

        AssemblyInterpreter interpreter = new AssemblyInterpreter();
        interpreter.execute(program);
    }
}
