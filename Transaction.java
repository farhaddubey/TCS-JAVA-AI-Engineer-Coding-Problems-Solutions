import java.util.*;

class Record {
    // One ledger entry beloning to a transaction
    String txnId; 

    // Account particapting in transactions 
    String account; 

    // Negative = debit, Positive = Credit 
    int amount; 

    // Transaction event time 
    long timestamp; 

    Record(String txnId, String account, int amount, long timestamp) {
        this.txnId = txnId; 
        this.account = account; 
        this.amount = amount; 
        this.timestamp = timestamp; 
    }
}

public class Transaction {
    // List<txn_id> -> which is String 
    public static List<String> validTransactions(List<Record> records) {

        // SAME txnId => GROUP RECORDS 
        Map<String, List<Record>> map = new HashMap<>(); 

        // Grouping : O(n) 
        for (Record r : records) {
            // Creating buccket for each txnId -> [RECRODS, RECORDS, RECORDS]
            map.computeIfAbsent(r.txnId, k -> new ArrayList<>()).add(r); 
        }
        
        // Storing all valid transactions ids 
        List<String> answer = new ArrayList<>(); 

        // Validation : O(n) 
        // Validating each transaction independently 
        for (String txnId : map.keySet()) {
            List<Record> list = map.get(txnId); 

            // One debit + One credit => Exactly 2 entries 
            if (list.size() != 2) {
                continue; 
            }

            Record debit = null; 
            Record credit = null; 

            // Assigning the debit and credit as seperate entitites 
            for (Record r : list) {
                if (r.amount < 0) {
                    debit = r; 
                }
                if (r.amount > 0) {
                    credit = r; 
                }
            }

            // Must contain both sides Even is one side is null then WRONG ENTRY
            if (debit == null || credit == null) {
                continue; 
            }

            // Money conservation rule 
            if (Math.abs(debit.amount) != credit.amount) {
                continue; 
            }

            // Sender and Recerive cannot be same 
            if (debit.account.equals(credit.account)) {
                continue; 
            }

            if (debit.timestamp >= credit.timestamp) {
                continue; 
            }

            if (credit.timestamp - debit.timestamp > 100) {
                continue; 
            }

            answer.add(txnId); 
        }

        return answer; 
    }
}
