package com.arfaouiKallebi.JournalWeb.controller.api;
import com.arfaouiKallebi.JournalWeb.dto.ItemDTO;
import com.arfaouiKallebi.JournalWeb.model.Attachment;
import com.arfaouiKallebi.JournalWeb.model.Item;
import io.swagger.annotations.Api;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Api("items")
public interface ItemControllerApi {

    @GetMapping(value = "{idman}/items/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ItemDTO getItemById(@PathVariable Long id) ;

    @DeleteMapping(value = "items/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteItemById(@PathVariable Long id) ;

    @PostMapping(value = "{idman}/items/save", produces = MediaType.APPLICATION_JSON_VALUE)
    public ItemDTO saveItem(@PathVariable Long idman , @RequestParam("model") String item , @RequestParam("file") MultipartFile file) throws Exception ;
    @GetMapping(value = "{idman}/items/{iditem}/download/{fileId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Resource> downloadFile(@PathVariable Long iditem , @PathVariable String fileId) throws Exception  ;
    @GetMapping(value = "{idman}/items", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ItemDTO> getItems(@PathVariable Long idman) ;
}
