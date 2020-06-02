package com.desenvolvimento.bets4you.storage.local;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.desenvolvimento.bets4you.storage.FotoStorage;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;

import static java.nio.file.FileSystems.getDefault;

@Profile("local")
@Component
public class FotoStorageLocal implements FotoStorage{
	
	private static final Logger logger = LoggerFactory.getLogger(FotoStorageLocal.class);
	
	private Path local;
	
	public FotoStorageLocal() {
		this(getDefault().getPath(System.getenv("HOME"), "img-bets4you"));
		
	}
	
	public FotoStorageLocal(Path path) {
		this.local = path;
		criarPastas();
	}
		
	@Override
	public String salvar(MultipartFile[] files) { //aula 14.6

		String novoNome = null;
		if (files != null && files.length > 0) {
			MultipartFile arquivo = files[0];
			novoNome = renomearArquivo(arquivo.getOriginalFilename());
			File file = new File(this.local.toAbsolutePath().toString() + getDefault().getSeparator() + novoNome);
			file.setExecutable(true);
			file.setExecutable(true, false);
			try {
				arquivo.transferTo(file);
			} catch (IOException e) {
				throw new RuntimeException("Erro salvando a foto", e);
			}
		}

		try {
			Thumbnails.of(this.local.resolve(novoNome).toString()).size(65, 65).toFiles(Rename.PREFIX_DOT_THUMBNAIL);
		} catch (IOException e) {
			throw new RuntimeException("Erro gerando thumbnail", e);
		}

		return novoNome;
	}

	@Override
	public byte[] recuperar(String nome) { //aula 15.3
		try {
				return Files.readAllBytes(this.local.resolve(nome));
		} catch (IOException e) {
			throw new RuntimeException("Erro lendo a foto", e);
		}
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
		return "http://localhost:8080/fotos/" + foto;
	}


	private void criarPastas() { //aula 14.5
		try {
			Files.createDirectories(this.local);

			if(logger.isDebugEnabled()) {
				logger.debug("Pastas criadas para salvar foto...");
				logger.debug("Pasta default: " + this.local.toAbsolutePath());
			}
		} catch (IOException e) {
			throw new RuntimeException("");
		}
		
	}

	



	
}
