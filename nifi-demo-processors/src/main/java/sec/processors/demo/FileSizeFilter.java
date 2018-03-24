package sec.processors.demo;

import org.apache.nifi.annotation.behavior.ReadsAttribute;
import org.apache.nifi.annotation.behavior.ReadsAttributes;
import org.apache.nifi.annotation.behavior.WritesAttribute;
import org.apache.nifi.annotation.behavior.WritesAttributes;
import org.apache.nifi.annotation.documentation.CapabilityDescription;
import org.apache.nifi.annotation.documentation.SeeAlso;
import org.apache.nifi.annotation.documentation.Tags;
import org.apache.nifi.annotation.lifecycle.OnScheduled;
import org.apache.nifi.components.PropertyDescriptor;
import org.apache.nifi.flowfile.FlowFile;
import org.apache.nifi.processor.*;
import org.apache.nifi.processor.exception.ProcessException;
import org.apache.nifi.processor.util.StandardValidators;

import java.util.*;

@Tags({"example"})
@CapabilityDescription("Provide a description")
@SeeAlso({})
@ReadsAttributes({@ReadsAttribute(attribute="", description="")})
@WritesAttributes({@WritesAttribute(attribute="", description="")})
public class FileSizeFilter extends AbstractProcessor {

    private static int MAX_FILE_SIZE = 0;

    // Relationships
    public static final Relationship REL_SUCCESS = new Relationship.Builder()
            .name("pass the file")
            .description("This file will be passed on to success.")
            .build();
    public static final Relationship REL_FAILURE = new Relationship.Builder()
            .name("do not pass the file")
            .description("This file will not be passed on to success.")
            .build();


    public static final PropertyDescriptor MAX_FILE_SIZE_ATTRIBUTE_PROPERTY = new PropertyDescriptor.Builder()
            .name("Max file size attribute")
            .displayName("This is the name of the attribute that contains the file size")
            .description("Example Property")
            .required(true)
            .defaultValue("some.file.size")
            .addValidator(StandardValidators.NON_EMPTY_VALIDATOR)
            .build();


    private List<PropertyDescriptor> descriptors;

    private Set<Relationship> relationships;

    @Override
    protected void init(final ProcessorInitializationContext context) {
        final List<PropertyDescriptor> descriptors = new ArrayList<PropertyDescriptor>();
        descriptors.add(MAX_FILE_SIZE_ATTRIBUTE_PROPERTY);
        this.descriptors = Collections.unmodifiableList(descriptors);

        final Set<Relationship> relationships = new HashSet<Relationship>();
        relationships.add(REL_SUCCESS);
        relationships.add(REL_FAILURE);
        this.relationships = Collections.unmodifiableSet(relationships);
    }

    @Override
    public Set<Relationship> getRelationships() {
        return this.relationships;
    }

    @Override
    public final List<PropertyDescriptor> getSupportedPropertyDescriptors() {
        return descriptors;
    }

    @OnScheduled
    public void onScheduled(final ProcessContext context) {

    }

    @Override
    public void onTrigger(final ProcessContext context, final ProcessSession session) throws ProcessException {
        FlowFile flowFile = session.get();
        if ( flowFile == null ) {
            return;
        }
        String attributeMax = context.getProperty(MAX_FILE_SIZE_ATTRIBUTE_PROPERTY).getValue();
        getLogger().info("Got a flow file.");
        if(flowFile.getAttribute(attributeMax) != null){
            MAX_FILE_SIZE = Integer.parseInt(flowFile.getAttribute(attributeMax));
            getLogger().info("Reset MAX file size value to " + MAX_FILE_SIZE + " Attribute ref: " + attributeMax);
            session.remove(flowFile);
            return;
        }

        if(flowFile.getSize() < MAX_FILE_SIZE){
            getLogger().info("file passed, size is less than  " + MAX_FILE_SIZE + "( " + flowFile.getSize() + ")");
            session.transfer(flowFile,REL_SUCCESS);
        }else{
            getLogger().info("file NOT passed, size is more than  " + MAX_FILE_SIZE + "( " + flowFile.getSize() + ")");
            session.transfer(flowFile,REL_FAILURE);
        }

        System.out.println("Received a flow file.");
    }
}

