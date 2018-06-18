package br.unirio.calls.domains.call;

import java.util.Collection;

public interface CallFieldRepository{
    public Collection<CallField> findByCallId(int callId);

    public CallField findById(int id);

    public boolean save(CallField field);

    public boolean delete(CallField field);
}