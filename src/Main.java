public class Main {
    public static void main(String[] args) throws Exception {
        LogReader fileReader = new LogReader();
        var logLines = fileReader.ReadLogs();
        System.out.println(logLines.size());
        FindLineClass clS = new FindLineClass();

        clS.CheckSendResponsePairs(logLines);

        long duplicates = clS.CountDuplicates(logLines);
        System.out.println("Duplicates found: " + duplicates);
    }
}

