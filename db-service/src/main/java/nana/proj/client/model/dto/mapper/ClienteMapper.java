package nana.proj.client.model.dto.mapper;

import org.modelmapper.ModelMapper;

import nana.proj.client.model.ClienteModel;
import nana.proj.client.model.dto.ClienteCreateDto;
import nana.proj.client.model.dto.ClienteResponseDto;

public class ClienteMapper {
	
	public static ClienteModel toCliente(ClienteCreateDto createDto) {
		return new ModelMapper().map(createDto, ClienteModel.class);
	}
	public static ClienteResponseDto toDto(ClienteModel cliente) {
		return new ModelMapper().map(cliente, ClienteResponseDto.class);
	}

}
