import java.util.*; 

class Record {
    // One ledger entry belonging to a transaction 
    String txnId; 
    
    // Account particaipating in txn 
    String account; 

    // Negative = Debit Positive  = Credit 
    int amount; 

    // Transaction Event time 
    long timestamp; 

    Record(String txnId, String account, int amount, long timestamp) {
        this.txnId = txnId; 
        this.account = account; 
        this.amount = amount; 
        this.timestamp = timestamp; 
    }
}

public class AccountRecord {
    public static List<String> validTransactions(List<Record> records) {
        // SAME txn_id => Group Records 
        Map<String, List<Record>> map = new HashMap(); 

        for (Record r : records) {
            // Creating bucket for each txn Id 
            map.computeIfAbsent(r.txnId, k -> new ArrayList<>()).add(r); 
            // Ror each transaction id we are adding the entire object 
        }

        // Storing all valid transactions ids 
        List<String> answer = new ArrayList<>();
        
        // Validating each transaction independently 
        for (String txnId : map.keySet()) {
            List<Record> list = map.get(txnId); 

            // One debit + Onde credit exactly 2 entry 
            if (list.size() != 2) {
                continue; 
            }

            Record debit = null; 
            Record credit = null; 

            // Separting debit and credit entries 
            for (Record r : list) {
                if (r.amount < 0) {
                    debit = r; 
                }

                if (r.amount > 0) {
                    credit = r; 
                }
            }

            // Must contain both sides 
            if (debit == null || credit == null) {
                continue; 
            }

            // Money conservation rule 
            if (Math.abs(debit.amount) != credit.amount) {
                continue; 
            }

            // Sender and Receive cannot be same 
            if (debit.account.equals(credit.account)) {
                continue; 
            }

            // Debit must happen frist 
            if (debit.timestamp >= credit.timestamp) {
                continue; 
            }

            // Time window validatiom 
            if (credit.timestamp - debit.timestamp > 100) {
                continue; 
            }

            // All conditions passes that means valid transactions 
            answer.add(txnId); 
        }
        return answer; 
    }
}