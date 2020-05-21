package com.desenvolvimento.bets4you.storage.s3;

import com.desenvolvimento.bets4you.storage.FotoStorage;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Profile("producao")
@Component
public class FotoStorageS3 implements FotoStorage {

    @Override
    public String salvar(MultipartFile[] files) {
        return null;
    }

    @Override
    public byte[] recuperar(String foto) {
        return new byte[0];
    }

    @Override
    public byte[] recuperarThumbnail(String fotoEquipe) {
        return new byte[0];
    }

    @Override
    public void excluir(String foto) {

    }

    @Override
    public String getUrl(String foto) {
        return null;
    }
}
