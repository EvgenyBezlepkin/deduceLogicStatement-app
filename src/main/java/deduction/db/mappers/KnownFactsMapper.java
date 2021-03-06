package deduction.db.mappers;

import deduction.db.dto.KnownFactsDTO;


import java.util.Set;

public interface KnownFactsMapper {
    void insert(KnownFactsDTO knownfacts);
    Set<String> getKnownFacts(String name);
    void deleteKnownFacts(String name);
}

