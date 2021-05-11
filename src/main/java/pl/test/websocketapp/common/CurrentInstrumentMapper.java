package pl.test.websocketapp.common;

import org.mapstruct.Mapper;
import pl.test.websocketapp.message.Response;
import pl.test.websocketapp.obj.CurrentInstrument;

@Mapper(componentModel = "spring")
public interface CurrentInstrumentMapper {

    CurrentInstrument convertToCurrentInstrument (Response response);

}

