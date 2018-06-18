package br.unirio.calls.domains.call;

public interface CallRepository{
   public Call findById(int id);

   public boolean save(Call call);
}