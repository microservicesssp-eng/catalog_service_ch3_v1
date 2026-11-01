custom_build (

     ref = 'catalog_service_ch3',

     command = './gradlew bootBuildImage --imageName $EXPECTED_REF',
     deps = ['build.gradle', 'src']
)

k8s_yaml(['k8s/deployment.yml', 'k8s/service.yml'])

k8s_resource('catalog-service', port_forwards=['9001'])
