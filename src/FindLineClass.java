import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FindLineClass {
    public void CheckSendResponsePairs(ArrayList<LogLine> logs) throws Exception {
        Map<String, String> sendResMap = new HashMap<>();

        int count = 0;

        for (LogLine line : logs) {
            var request = logs.stream()
                    .filter(logLine -> logLine.Id.equals(line.Id) && logLine.Type == LogType.Send)
                    .findFirst().orElse(null);

            var response = logs.stream()
                    .filter(logLine -> logLine.Id.equals(line.Id) && logLine.Type == LogType.Response)
                    .findFirst().orElse(null);

            if ((logs.contains(line.Type.Send) && logs.contains(line.Id)) == (logs.contains(line.Type.Response) && logs.contains(line.Id))
                    && IsCloserTimeResponse(request, response)) {
                sendResMap.put(String.valueOf(line.Type.Send), String.valueOf(line.Type.Response));
                count++;
            } else {
                throw new Exception("Error: no Response found for Send with ID ");
            }

        }
        System.out.println("Logs count:  " + count);
    }

    private boolean IsCloserTimeResponse(LogLine requestLine, LogLine responseLine) {
        if (requestLine == null || responseLine == null) {
            return false;
        }
        return (requestLine.Date.getTime() - responseLine.Date.getTime()) < 3;
    }

    public long CountDuplicates(ArrayList<LogLine> logs) {
        Map<String, List<LogLine>> matches = logs.stream()
                .collect(Collectors.groupingBy(LogLine::GetIdAndType));

        var incorrectGroups = matches.entrySet().stream()
                .filter(group -> group.getValue().stream().count() > 1);

        return incorrectGroups.count();
    }
}