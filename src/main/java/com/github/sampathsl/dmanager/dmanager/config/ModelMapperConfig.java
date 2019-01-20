package com.github.sampathsl.dmanager.dmanager.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.NamingConventions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

  @Bean
  public ModelMapper modelMapper() {
    ModelMapper modelMapper = new ModelMapper();

    /*Converter<DownloadSession, DownloadSessionDto> converter =
        new Converter<DownloadSession, DownloadSessionDto>() {

          @Override
          public DownloadSessionDto convert(
              MappingContext<DownloadSession, DownloadSessionDto> context) {
            DownloadSession source = context.getSource();
            DownloadSessionDto destination = new DownloadSessionDto();

            List<DownloadTask> userCategoryValues = source.getDownloadTasks();
            ArrayList<String> urls = new ArrayList<>();
            ArrayList<DownloadTask> userCategoryToMap = new ArrayList<DownloadTask>();

            for (final DownloadTask userCategory : userCategoryValues) {
              urls.add(userCategory.getFileSource());
              userCategoryToMap.add(userCategory);
            }

            java.lang.reflect.Type targetListType =
                new TypeToken<List<DownloadTaskDto>>() {}.getType();
            ArrayList<DownloadTaskDto> personDTOs =
                modelMapper.map(userCategoryToMap, targetListType);

            destination.setDownloadTasks(personDTOs);
            destination.setUrls(urls);

            return destination;
          }
        };

    modelMapper
        .createTypeMap(DownloadSession.class, DownloadSessionDto.class)
        .setConverter(converter);*/

    modelMapper.getConfiguration().setFieldMatchingEnabled(true);
    modelMapper
        .getConfiguration()
        .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);
    modelMapper.getConfiguration().setSourceNamingConvention(NamingConventions.JAVABEANS_MUTATOR);

    return modelMapper;
  }
}
