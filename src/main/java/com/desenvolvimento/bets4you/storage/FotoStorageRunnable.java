package com.desenvolvimento.bets4you.storage;

import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import com.desenvolvimento.bets4you.dto.FotoDTO;

public class FotoStorageRunnable implements Runnable { //aula 14.4

	
	private MultipartFile[] files;
	private DeferredResult<FotoDTO> resultado;
	private FotoStorage fotoStorage;
	
	public FotoStorageRunnable(MultipartFile[] files, DeferredResult<FotoDTO> resultado, FotoStorage fotoStorage) {
		this.files = files;
		this.resultado = resultado;
		this.fotoStorage = fotoStorage;
	}

	@Override
	public void run() {
		String nomeFoto = this.fotoStorage.salvar(files);
		String contentType = files[0].getContentType();
		resultado.setResult(new FotoDTO(nomeFoto, contentType, fotoStorage.getUrl(nomeFoto)));
	}

}
