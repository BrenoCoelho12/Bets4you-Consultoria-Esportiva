package com.desenvolvimento.bets4you.storage;

import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface FotoStorage {

	public final String THUMBNAIL_PREFIX = "thumbnail.";

	public String salvar(MultipartFile[] files);

	public byte[] recuperar(String foto);

	public byte[] recuperarThumbnail(String fotoEquipe);

	public void excluir(String foto);

	String getUrl(String foto);

	default String renomearArquivo(String nomeOriginal) { //aula 14.7
		return UUID.randomUUID().toString() + "_" + nomeOriginal;
	}

}
